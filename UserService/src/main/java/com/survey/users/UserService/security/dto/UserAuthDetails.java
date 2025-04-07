package com.survey.users.UserService.security.dto;

import com.survey.users.UserService.domain.user.Role;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class UserAuthDetails {
    private final String email;
    private final String password;
    private final List<String> permissions;
    private final String role;
    private final List<Long> organizations;
}