package com.survey.users.UserService.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDto {

    @NotBlank
    private String oldUsername;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    private String firstName;
    private String lastName;

}
