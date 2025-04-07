package com.survey.users.SurveyService.repository;

import com.survey.users.SurveyService.domain.Question;
import com.survey.users.SurveyService.domain.Survey;
import com.survey.users.SurveyService.domain.SurveyHasQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyHasQuestionRepository extends JpaRepository<SurveyHasQuestion, Long> {

    @Query("SELECT shq FROM SurveyHasQuestion shq WHERE shq.survey = :survey")
    List<SurveyHasQuestion> findQuestionsBySurvey(@Param("survey") Survey survey);

    @Query("SELECT shq FROM SurveyHasQuestion shq WHERE shq.survey = :survey AND shq.number = :number")
    SurveyHasQuestion findQuestionBySurveyAndNumber(@Param("survey") Survey survey, @Param("number") int number);
}
