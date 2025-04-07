package com.survey.users.SurveyService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name="survey")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Lob
    private byte[] logo;
    private Long organizationId;
    private Long userId;
    private boolean priv;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant updatedDate;
    private Timestamp activationDate;
    private Timestamp deactivationDate;
    private boolean active;
}
