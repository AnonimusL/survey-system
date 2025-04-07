package com.survey.users.SurveyService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="scale_has_value")
@Getter
@Setter
public class ScaleHasValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Scale scale;
    @ManyToOne
    private ScaleValue scaleValue;
}
