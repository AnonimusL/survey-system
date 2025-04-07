package com.survey.users.UserService.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "survey_user", indexes = {@Index(columnList = "username", unique = true), @Index(columnList = "username, password", unique = true), @Index(columnList = "email", unique = true)})
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Column(unique = true)
    @NotBlank
    @Email
    private String email;

    private String firstName;
    private String lastName;

    @ManyToOne
    private Role role;

    @OneToMany
    private List<Organization> organizations;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean disabled;
}
