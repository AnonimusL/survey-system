package com.survey.users.SurveyService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("RATING_SCALE")
@Getter
@Setter
public class RatingScaleQuestion extends Question{

    @ManyToOne
    private Scale scale;
}
