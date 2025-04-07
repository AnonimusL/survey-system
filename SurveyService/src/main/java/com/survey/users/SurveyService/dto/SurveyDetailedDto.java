package com.survey.users.SurveyService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SurveyDetailedDto extends SurveyDto {
    private List<QuestionForSurveyDto> questionForSurveyDto;
}
