package com.survey.users.SurveyService.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceForSurveyDto.class, name = "MULTIPLE_CHOICE"),
        @JsonSubTypes.Type(value = RatingScaleForSurveyDto.class, name = "RATING_SCALE"),
        @JsonSubTypes.Type(value = OpenEndedForSurveyDto.class, name = "OPEN_ENDED"),
        @JsonSubTypes.Type(value = GradeForSurveyDto.class, name = "GRADE")
})
public class QuestionForSurveyDto {
    private Long surveyId;
    private int number;
    private String text;
    private String type;
}
