package com.survey.users.SurveyService.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class SurveyCreateDto {

    private String title;
    private String description;
    private byte[] logo;
    private Long organizationId;
    private Long userId;
    private boolean priv;
    private Timestamp activationDate;
    private Timestamp deactivationDate;
    private boolean active;
}
