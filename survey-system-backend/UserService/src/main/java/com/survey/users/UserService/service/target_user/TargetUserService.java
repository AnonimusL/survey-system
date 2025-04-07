package com.survey.users.UserService.service.target_user;
import com.survey.users.UserService.client.SurveyRestClient;
import com.survey.users.UserService.domain.target_user.Connection;
import com.survey.users.UserService.domain.target_user.Connector;
import com.survey.users.UserService.domain.target_user.TargetUser;
import com.survey.users.UserService.dto.for_target_user.CreateConnections;
import com.survey.users.UserService.dto.for_target_user.ForActivationDto;
import com.survey.users.UserService.dto.for_target_user.SurveyDto;
import com.survey.users.UserService.dto.message.MessageDto;
import com.survey.users.UserService.producer.RabbitMQProducer;
import com.survey.users.UserService.repository.target_user.ConnectionsRepository;
import com.survey.users.UserService.repository.target_user.ConnectorRepository;
import com.survey.users.UserService.repository.target_user.TargetUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TargetUserService {

    private TargetUserRepository userRepository;
    private ConnectorRepository connectorRepository;
    private ConnectionsRepository connectionRepository;
    private RabbitMQProducer rabbitMQProducer;
    private SurveyRestClient surveyRestClient;

    public List<TargetUser> getUsers(){
        return userRepository.findAll();
    }

    public List<SurveyDto> getSurveysForUser(String email) {
        TargetUser user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(String.format("User with email %s not found", email)));;
        List<Connection> connections = connectionRepository.findByUser(user);

        if(connections.isEmpty()) return new ArrayList<>();

        List<String> surveyIdentifiers = new ArrayList<>();
        for(Connection c : connections){
            surveyIdentifiers.add(c.getConnector().getSurveyIdentifier());
        }

        try {
            return surveyRestClient.getSurveys(surveyIdentifiers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SurveyDto> getSurveysInstances(String identifier) {
        List<Connector> connectors = connectorRepository.findBySurveyIdentifier(identifier);
        List<String> surveyIdentifiers = new ArrayList<>();
        for(Connector c : connectors){
            surveyIdentifiers.add(c.getSurveyIdentifier());
        }

        try {
            return surveyRestClient.getSurveys(surveyIdentifiers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MessageDto activate(ForActivationDto forActivationDto) {
        List<Connection> surveyConnections = connectionRepository.findBySurveyIds(forActivationDto.getSurveyIds());

        for(Connection surveyConnection : surveyConnections){
            surveyConnection.setActiveFrom(forActivationDto.getStart());
            surveyConnection.setActiveTo(forActivationDto.getEnd());
        }

        connectionRepository.saveAll(surveyConnections);

        return new MessageDto("Successfully activated");
    }

    public MessageDto makeConnection(CreateConnections createConnections){
        List<TargetUser> users = userRepository.findByAdditionalData(createConnections.getConditions().keySet().stream().toList(), createConnections.getConditions().values().stream().toList());
        List<Connection> connections = new ArrayList<>();
        if(createConnections.getConnectorId().isEmpty()){
            List<Connector> connectors = connectorRepository.findBySurveyIdentifier(createConnections.getSurveyId());
            for(Connector connector : connectors){
                for(TargetUser tu : users){
                    Connection connection = new Connection();
                    connection.setUser(tu);
                    connection.setConnector(connector);
                    connections.add(connection);
                }
            }
        }else {
            Connector connector = connectorRepository.findByUniqueIdentifierAndSurveyIdentifier(createConnections.getConnectorId(), createConnections.getSurveyId());
            for(TargetUser tu : users){
                Connection connection = new Connection();
                connection.setUser(tu);
                connection.setConnector(connector);
                connections.add(connection);
            }
        }
        connectionRepository.saveAll(connections);

        return new MessageDto("Connections successfully made");
    }
}
