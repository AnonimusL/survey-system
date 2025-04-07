package com.survey.users.UserService.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateDto {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private Long organization;

}
