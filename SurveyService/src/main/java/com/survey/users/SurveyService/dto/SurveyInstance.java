package com.survey.users.SurveyService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyInstance {
    private Long id;
    private String subject;
    private String title;
    private String group;
    private boolean activated;
    private String from;
    private String to;
}
