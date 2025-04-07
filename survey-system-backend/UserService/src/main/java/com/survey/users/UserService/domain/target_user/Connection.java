package com.survey.users.UserService.domain.target_user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "user_connections")
@Getter
@Setter
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TargetUser user;
    @ManyToOne
    private Connector connector;
    private Timestamp activeFrom;
    private Timestamp activeTo;
    private boolean filled;
}
