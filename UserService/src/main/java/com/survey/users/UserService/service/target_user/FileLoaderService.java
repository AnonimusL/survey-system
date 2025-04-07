package com.survey.users.UserService.service.target_user;

import com.survey.users.UserService.domain.target_user.Connector;
import com.survey.users.UserService.domain.target_user.TargetUser;
import com.survey.users.UserService.domain.user.Organization;
import com.survey.users.UserService.dto.for_target_user.ConfigMapping;
import com.survey.users.UserService.dto.for_target_user.LoadedUsers;
import com.survey.users.UserService.repository.target_user.ConnectionsRepository;
import com.survey.users.UserService.repository.target_user.ConnectorRepository;
import com.survey.users.UserService.repository.target_user.TargetUserRepository;
import com.survey.users.UserService.repository.user.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
@AllArgsConstructor
public class FileLoaderService {

    private OrganizationRepository organizationRepository;
    private TargetUserRepository targetUserRepository;
    private ConnectorRepository connectorRepository;
    private ConnectionsRepository connectionsRepository;

    public LoadedUsers loadUserData(Long organizationId, MultipartFile file, MultipartFile config, Map<Integer, String> emailRules, boolean emailExists, boolean hasHeader){
        List<ConfigMapping> columnMappings = new ArrayList<>();
        LoadedUsers loadedUsers = new LoadedUsers();

        try {
            columnMappings = readConfig(config);
        }catch (IOException e){
            return loadedUsers;
        }

        System.out.println(columnMappings);

        Map<Integer, String> mappings = new HashMap<>();
        for(ConfigMapping configMapping : columnMappings) {
            mappings.put(configMapping.getIndex(), configMapping.getOriginal());
        }

        List<TargetUser> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            // Parse the CSV file
            CSVParser csvParser;
            if(hasHeader)
                csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            else
                csvParser = CSVFormat.DEFAULT.parse(reader);

            Organization o = organizationRepository.getReferenceById(organizationId);
            // Iterate through records
            for (CSVRecord record : csvParser) {
                TargetUser targetUser = new TargetUser();
                Map<String, Object> additionalData = new HashMap<>();
                for (ConfigMapping entry : columnMappings) {
                    int columnIndex = entry.getIndex();

                    if(mappings.get(columnIndex).equals("email")) {
                        targetUser.setEmail(record.get(columnIndex));
                    }else {
                        additionalData.put(entry.getCustom(), record.get(entry.getIndex()));
                    }
                }
                if(targetUser.getEmail().isEmpty() && emailExists) {
                    loadedUsers.getFail().add(targetUser);
                } else if(!emailExists) {
                    // make email by rules
                }
                targetUser.setOrganization(o);
                targetUser.setAdditionalData(additionalData);
                targetUser = targetUserRepository.save(targetUser);
                loadedUsers.getSuccess().add(targetUser);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return loadedUsers;
    }

    public List<Connector> loadConnectors(MultipartFile file, MultipartFile config, String surveyId, boolean hasHeader) {
        List<ConfigMapping> columnMappings = new ArrayList<>();
        try {
            columnMappings = readConfig(config);
        }catch (IOException e){
            return new ArrayList<>();
        }
        System.out.println(columnMappings);

        List<Connector> connectors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            // Parse the CSV file
            CSVParser csvParser;
            if(hasHeader)
                csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            else
                csvParser = CSVFormat.DEFAULT.parse(reader);

            for (CSVRecord record : csvParser) {
                Connector connector = new Connector();
                Map<String, Object> additionalData = new HashMap<>();

                for (ConfigMapping entry : columnMappings) {
                    int columnIndex = entry.getIndex();
                    if (columnIndex == -1) continue;
                    additionalData.put(entry.getCustom(), record.get(columnIndex));
                }
                connector.setUniqueIdentifier(UUID.randomUUID().toString());
                connector.setSurveyIdentifier(surveyId);
                connector.setAdditionalData(additionalData);
                connector = connectorRepository.save(connector);
                connectors.add(connector);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return connectors;
    }

    private static List<ConfigMapping>  readConfig(MultipartFile file) throws IOException {
        List<ConfigMapping> mappings = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splitLine = line.split(" ", 3);

            mappings.add(new ConfigMapping(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2]));
        }

        reader.close();


        return mappings;
    }

    private static String replaceSpecialCharacters(String input) {
        // Replace ž with z, š with s, đ with dj, č with c, ć with c
        return input
                .replace("ž", "z")
                .replace("š", "s")
                .replace("đ", "dj")
                .replace("č", "c")
                .replace("ć", "c");
        // Add more replacements as needed
    }

}
