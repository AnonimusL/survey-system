package com.survey.users.SurveyService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NodeDto {
    private Long id;
    private Long idParent;
    private String name;
    private String domain;
    private Long organizationId;
    private Long userId;
    private SurveyInfoDto surveyInfoDto;
}
