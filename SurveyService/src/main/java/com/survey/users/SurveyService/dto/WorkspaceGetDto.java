package com.survey.users.SurveyService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceGetDto {
    private Long userId;
    private Long organizationId;
    private String domain;
}
