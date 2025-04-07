package com.survey.users.UserService.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_organization")
@Getter
@Setter
public class UserHasOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Organization organization;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean active;

}
