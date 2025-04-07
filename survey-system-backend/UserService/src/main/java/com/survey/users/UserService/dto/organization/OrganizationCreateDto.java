package com.survey.users.UserService.dto.organization;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationCreateDto {
    @NotBlank
    private String name;
    private AddressDto address;
    private String emailFormat;
    private String phoneNumber;
    private String webpage;
    private byte[] logo;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AddressDto {

        @NotBlank
        private String country;
        @NotBlank
        private String city;
        private String postcode;
        private String street;
        private String number;
    }
}
