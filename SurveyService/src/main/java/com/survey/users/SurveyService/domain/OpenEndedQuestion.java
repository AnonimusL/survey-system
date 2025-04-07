package com.survey.users.SurveyService.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("OPEN_ENDED")
@Getter
@Setter
public class OpenEndedQuestion extends Question{
    @Column(name = "`limit`")
    private int limit;
}
