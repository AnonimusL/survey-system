package com.survey.users.UserService.dto.organization;

import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationDetailedDto {
    private String name;
    private String phoneNumber;
    private String webpage;
    private byte[] logo;
    private boolean active;
    private String about;
    private OrganizationCreateDto.AddressDto addressDto;

}
