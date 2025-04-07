package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.RatingScaleQuestion;
import com.survey.users.SurveyService.domain.Scale;
import com.survey.users.SurveyService.domain.ScaleHasValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScaleHasValueRepository extends JpaRepository<ScaleHasValue, Long> {

    List<ScaleHasValue> findAllByScale(Scale scale);
}
