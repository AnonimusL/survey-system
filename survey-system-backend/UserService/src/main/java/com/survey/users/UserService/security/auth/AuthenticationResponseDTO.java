package com.survey.users.UserService.security.auth;

import lombok.*;

@RequiredArgsConstructor
@Getter
public class AuthenticationResponseDTO {
    private final String jwt;
}
