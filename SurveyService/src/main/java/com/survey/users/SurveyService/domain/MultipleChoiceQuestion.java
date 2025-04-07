package com.survey.users.SurveyService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
@Getter
@Setter
public class MultipleChoiceQuestion extends Question {
    boolean allowMultiple;
}
