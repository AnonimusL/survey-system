package com.survey.users.SurveyService.domain.connector;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class SurveyConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentGroup;
    private String survey;
    private String surveyTitle;
    private String surveySubject;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean activated;
    private boolean scheduled;
}
