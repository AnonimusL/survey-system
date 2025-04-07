package com.survey.users.UserService.repository.target_user;

import com.survey.users.UserService.domain.target_user.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectorRepository extends JpaRepository<Connector, Long> {
    List<Connector> findBySurveyIdentifier(String identifier);

    Connector findByUniqueIdentifierAndSurveyIdentifier(String connectorId, String surveyId);
}
