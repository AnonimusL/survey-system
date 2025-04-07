package com.survey.users.UserService.dto.for_target_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
    private Map<String, Object> visibleData;
}
