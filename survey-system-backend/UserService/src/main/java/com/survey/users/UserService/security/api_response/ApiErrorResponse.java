package com.survey.users.UserService.security.api_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private ZonedDateTime timestamp;
    private String errorMessage;
    private ErrorCode errorCode;

}