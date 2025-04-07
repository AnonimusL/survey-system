package com.survey.users.UserService.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserFilterDto {
    private String firstName;
    private String lastName;
    private String role;
    private boolean deleted;
    private boolean disabled;
}
