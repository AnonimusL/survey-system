package com.survey.users.SurveyService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="scale_value")
@Getter
@Setter
public class ScaleValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`value`")
    private int value;
    private String shortDescription;
    private String description;
}
