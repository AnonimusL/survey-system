package com.survey.users.SurveyService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="domain", indexes = {@Index(columnList = "name", unique = true)})
@Getter
@Setter
public class Domain {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private Long organizationId;
    private Long userId;
    private boolean priv;
}
