package com.survey.users.SurveyService.service.dataret;

import com.survey.users.SurveyService.domain.connector.Connector;
import com.survey.users.SurveyService.domain.connector.SurveyConnection;
import com.survey.users.SurveyService.dto.connector.ConfigMapping;
import com.survey.users.SurveyService.repository.connector.ConnectorRepository;
import com.survey.users.SurveyService.repository.connector.SurveyConnectionRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@AllArgsConstructor
public class FileLoader {

    private ConnectorRepository connectorRepository;
    private SurveyConnectionRepository surveyConnectionRepository;

    public List<Connector> loadApache(String filePath, String configPath) {
        List<ConfigMapping> columnMappings = new ArrayList<>();
        FileWriter fw;
        Map<String, Integer> mapa = new HashMap<>();

        try {
            columnMappings = readConfig(configPath);
        }catch (IOException e){
            return new ArrayList<>();
        }
        System.out.println(columnMappings);

        Map<Integer, String> mappings = new HashMap<>();
        for(ConfigMapping configMapping : columnMappings) {
            mappings.put(configMapping.getIndex(), configMapping.getOriginal());
        }

        List<Connector> connectors = new ArrayList<>();
        Map<String, Set<String>> surveyGroups = new HashMap<>();
        Map<String, Set<String>> subjects = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader);

            for (CSVRecord record : parser) {
                Connector connector = new Connector();

                for (ConfigMapping entry : columnMappings) {
                    int columnIndex = entry.getIndex();

                    if (columnIndex == -1) continue;

                    switch (mappings.get(columnIndex)) {
                        case "name":
                            connector.setName(record.get(columnIndex));
                            break;
                        case "subject":
                            connector.setSubject(record.get(columnIndex));
                            break;
                        case "type":
                            connector.setType(record.get(columnIndex));
                            break;
                        case "person":
                            connector.setPerson(record.get(columnIndex));
                            if(mapa.containsKey(record.get(columnIndex))) {
                                int n = mapa.get(record.get(columnIndex));
                                n += (Integer.parseInt(record.get(5).split("-")[1]) - Integer.parseInt(record.get(5).split(":")[0]));
                                mapa.put(record.get(columnIndex), n);
                            }else {
                                int n = (Integer.parseInt(record.get(5).split("-")[1]) - Integer.parseInt(record.get(5).split(":")[0]));
                                mapa.put(record.get(columnIndex), n);
                            }

                            break;
                        case "group":
                            if(!surveyGroups.containsKey(connector.getPerson()+"+"+connector.getSubject())){
                                surveyGroups.put(connector.getPerson()+"+"+connector.getSubject(), new HashSet<>());
                            }
                            if(!subjects.containsKey(connector.getSubject())){
                                subjects.put(connector.getSubject(), new HashSet<>());
                            }
                            String[] splitGroups = record.get(columnIndex).split(",");
                            for(String group : splitGroups) {
                                surveyGroups.get(connector.getPerson() + "+" + connector.getSubject()).add(group.trim());
                                subjects.get(connector.getSubject()).add(group.trim());
                            }
                    }
                }
                connector = connectorRepository.save(connector);
                connectors.add(connector);
            }
            for(Map.Entry<String, Set<String>> entry : surveyGroups.entrySet()){
                for(String group : entry.getValue()) {
                    SurveyConnection surveyConnection = new SurveyConnection();
                    surveyConnection.setStudentGroup(group);
                    surveyConnection.setSurvey("NASTAVNIK_SURVEY");
                    surveyConnection.setSurveyTitle(entry.getKey().split("\\+")[0]);
                    surveyConnection.setSurveySubject(entry.getKey().split("\\+")[1]);
                    surveyConnectionRepository.save(surveyConnection);
                }
            }

            for(Map.Entry<String, Set<String>> entry : subjects.entrySet()){
                for(String group : entry.getValue()) {
                    SurveyConnection surveyConnection = new SurveyConnection();
                    surveyConnection.setStudentGroup(group);
                    surveyConnection.setSurvey("PREDMET_SURVEY");
                    surveyConnection.setSurveyTitle(entry.getKey());
                    surveyConnection.setSurveySubject(entry.getKey());
                    surveyConnectionRepository.save(surveyConnection);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return connectors;
    }

    private static List<ConfigMapping>  readConfig(String filePath) throws FileNotFoundException {
        List<ConfigMapping> mappings = new ArrayList<>();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ", 3);

            mappings.add(new ConfigMapping(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2]));
        }

        scanner.close();


        return mappings;
    }
}
