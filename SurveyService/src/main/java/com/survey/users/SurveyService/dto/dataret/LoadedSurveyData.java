package com.survey.users.SurveyService.dto.dataret;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoadedSurveyData {
    private String id_pitanja;
    private String tekst;
    private String tip;
    private String format;
}
