package com.survey.users.SurveyService.service;

import com.survey.users.SurveyService.domain.connector.SurveyConnection;
import com.survey.users.SurveyService.dto.*;
import com.survey.users.SurveyService.dto.connector.SurveyActivation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SurveyService {

    Page<SurveyDto> getSurveyForOrganization(Long organization, Pageable pageable);
    SurveyDto getSurvey(Long id);

    SurveyDto createSurvey(SurveyCreateDto surveyCreateDto);

    SurveyDto updateSurveyData(SurveyUpdateDto surveyUpdateDto);
    MessageDto addQuestionsForSurvey(QuestionForSurveyDto questionsForSurveyDto);

    MessageDto updateQuestionsForSurvey(QuestionForSurveyDto questionForSurveyDto);

    MessageDto deleteQuestionForSurvey(Long id);

    Page<SurveyDto> getMySurveyForOrganization(Long id, Long idx, Pageable pageable);

    SurveyDetailedDto getSurveyDetailed(Long id);

    List<QuestionForSurveyDto> getQuestions(Long id);

    SurveyDto activate(Long id, ForActivationDto forActivationDto);

    List<SurveyInstance> getInstances(String name);

    List<SurveyInstance> activateSurveysInstances(SurveyActivation surveyActivation);

    List<SurveyConnection> getSurveysForUser(String group);
}
