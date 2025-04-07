package com.survey.users.SurveyService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class SurveyDto {
    private Long id;
    private String title;
    private String description;
    private byte[] logo;
    private boolean priv;
    private Timestamp activationDate;
    private Timestamp deactivationDate;
    private boolean active;
    private Long organizationId;
    private Long userId;
}
