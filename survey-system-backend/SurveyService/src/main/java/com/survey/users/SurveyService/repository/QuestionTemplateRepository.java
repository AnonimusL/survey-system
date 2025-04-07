package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.QuestionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuestionTemplateRepository extends JpaRepository<QuestionTemplate, Long> {
}
