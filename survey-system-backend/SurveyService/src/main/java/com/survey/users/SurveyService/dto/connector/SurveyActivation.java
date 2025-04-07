package com.survey.users.SurveyService.dto.connector;

import com.survey.users.SurveyService.dto.SurveyInstance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyActivation {
    private String activeFrom;
    private String activeTo;
    private String surveyName;
    private List<SurveyInstance> surveys;
}
