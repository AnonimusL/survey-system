package com.survey.users.SurveyService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NodeCreateDto {
    private Long idParent;
    private String name;
    private String domain;
    private Long organizationId;
    private Long userId;
}
