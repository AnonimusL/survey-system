package com.survey.users.UserService.domain.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    private String postcode;
    private String street;
    private String number;
}
