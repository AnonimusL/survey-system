package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.Scale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, Long> {
}
