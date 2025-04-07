package com.survey.users.SurveyService.domain.connector;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "connectors")
@Getter
@Setter
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subject;
    private String type;
    private String person;
}
