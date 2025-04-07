package com.survey.users.UserService.dto.for_target_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateConnections {
    private String surveyId;
    private String connectorId;
    private Map<String, String> conditions;
}
