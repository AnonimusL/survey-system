package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.ScaleValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScaleValueRepository extends JpaRepository<ScaleValue, Long> {
}
