package com.survey.users.SurveyService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("GRADE_CHOICE")
@Getter
@Setter
public class GradeQuestion extends Question{
    private float minGrade;
    private float maxGrade;
}
