package com.survey.users.SurveyService.repository.connector;

import com.survey.users.SurveyService.domain.connector.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorRepository extends JpaRepository<Connector, Long> {
}
