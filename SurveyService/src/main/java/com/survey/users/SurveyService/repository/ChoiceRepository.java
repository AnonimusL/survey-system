package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.Choice;
import com.survey.users.SurveyService.domain.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findChoicesByMultipleChoiceQuestionOrderByNum(MultipleChoiceQuestion multipleChoiceQuestion);
}
