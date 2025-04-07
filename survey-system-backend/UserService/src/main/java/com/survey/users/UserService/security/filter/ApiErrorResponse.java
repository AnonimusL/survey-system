package com.survey.users.UserService.security.filter;

import com.survey.users.UserService.security.api_response.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {

    private ZonedDateTime time;
    private String message;
    private ErrorCode errorCode;
    public ApiErrorResponse(ZonedDateTime now, String message, ErrorCode errorCode) {
        this.time = now;
        this.message = message;
        this.errorCode = errorCode;
    }
}
