package com.survey.users.UserService.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="organization")
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Embedded
    private Address address;
    private String emailFormat;
    private String phoneNumber;
    private String webpage;
    @Lob
    private byte[] logo;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean active;
    private String about;

}
