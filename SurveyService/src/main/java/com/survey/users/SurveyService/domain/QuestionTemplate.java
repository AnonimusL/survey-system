package com.survey.users.SurveyService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question_template")
@Getter
@Setter
public class QuestionTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Domain domain;
    @ManyToOne
    private Question question;
}
