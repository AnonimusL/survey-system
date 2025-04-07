package com.survey.users.UserService.dto.for_target_user;

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
    private List<String> surveyIds;
    private Timestamp start;
    private Timestamp end;
}
