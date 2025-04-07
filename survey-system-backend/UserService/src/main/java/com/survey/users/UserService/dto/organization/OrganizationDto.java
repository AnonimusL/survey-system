package com.survey.users.UserService.dto.organization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationDto {
    private Long id;
    private String name;
    private String country;
    private String city;
}
