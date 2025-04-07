package com.survey.users.SurveyService.dto.connector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForActivationDto {
    private List<Long> surveyIds;
    private Timestamp start;
    private Timestamp end;
}
