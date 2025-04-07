package com.survey.users.UserService.dto.organization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationUpdateDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String webpage;
    private String emailFormat;
    private byte[] logo;
    private OrganizationCreateDto.AddressDto addressDto;

}
