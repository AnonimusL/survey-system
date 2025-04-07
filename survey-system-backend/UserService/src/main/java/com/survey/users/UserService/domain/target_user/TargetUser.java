package com.survey.users.UserService.domain.target_user;

import com.survey.users.UserService.domain.helper.JsonConverter;
import com.survey.users.UserService.domain.user.Organization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "target_users")
@Convert(attributeName = "additionalData", converter = JsonConverter.class)
@Getter
@Setter
public class TargetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @ManyToOne
    private Organization organization;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> additionalData;

}