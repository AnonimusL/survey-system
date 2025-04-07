package com.survey.users.SurveyService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RatingScaleForSurveyDto extends QuestionForSurveyDto {
    private Long scaleId;
    @JsonProperty("optionalField")
    private List<ScaleValueDto> scaleValueList;
}
