package com.survey.users.SurveyService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradeForSurveyDto extends QuestionForSurveyDto{
    private double grade;
}
