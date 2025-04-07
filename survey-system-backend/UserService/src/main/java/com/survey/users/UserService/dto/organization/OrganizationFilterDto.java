package com.survey.users.UserService.dto.organization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationFilterDto {
    private String name;
    private String country;
    private String city;
    private boolean active;
}
