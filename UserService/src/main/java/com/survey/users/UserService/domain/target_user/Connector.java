package com.survey.users.UserService.domain.target_user;

import com.survey.users.UserService.domain.helper.JsonConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "connectors")
@Convert(attributeName = "additionalData", converter = JsonConverter.class)
@Getter
@Setter
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueIdentifier;
    private String surveyIdentifier;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> additionalData;
}
