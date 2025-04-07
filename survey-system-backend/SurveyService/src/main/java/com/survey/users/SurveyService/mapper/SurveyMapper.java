package com.survey.users.SurveyService.mapper;

import com.survey.users.SurveyService.domain.*;
import com.survey.users.SurveyService.domain.connector.SurveyConnection;
import com.survey.users.SurveyService.domain.constants.QuestionType;
import com.survey.users.SurveyService.dto.*;
import com.survey.users.SurveyService.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SurveyMapper {

    private SurveyHasQuestionRepository surveyHasQuestionRepository;
    private ChoiceRepository choiceRepository;
    private ScaleHasValueRepository scaleHasValueRepository;
    private DomainRepository domainRepository;

    public SurveyDto surveyToSurveyDto(Survey survey){
        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setId(survey.getId());
        surveyDto.setLogo(survey.getLogo());
        surveyDto.setDescription(survey.getDescription());
        surveyDto.setTitle(survey.getTitle());
        surveyDto.setPriv(survey.isPriv());
        surveyDto.setActive(survey.isActive());
        surveyDto.setActivationDate(survey.getActivationDate());
        surveyDto.setDeactivationDate(survey.getDeactivationDate());
        surveyDto.setOrganizationId(survey.getOrganizationId());
        surveyDto.setUserId(survey.getUserId());
        return surveyDto;
    }

    public SurveyDetailedDto surveyToDetailedSurvey(Survey survey) {
        SurveyDetailedDto surveyDetailedDto = new SurveyDetailedDto();

        surveyDetailedDto.setDescription(survey.getDescription());
        surveyDetailedDto.setTitle(survey.getTitle());
        surveyDetailedDto.setId(survey.getId());
        surveyDetailedDto.setPriv(survey.isPriv());
        surveyDetailedDto.setActive(survey.isActive());
        surveyDetailedDto.setActivationDate(survey.getActivationDate());
        surveyDetailedDto.setDeactivationDate(survey.getDeactivationDate());
        surveyDetailedDto.setUserId(survey.getUserId());
        surveyDetailedDto.setOrganizationId(survey.getOrganizationId());

        surveyDetailedDto.setQuestionForSurveyDto(new ArrayList<>());
        List<SurveyHasQuestion> questions = surveyHasQuestionRepository.findQuestionsBySurvey(survey);
        List<QuestionForSurveyDto> questionForSurveyDtoList = new ArrayList<>();
        for(SurveyHasQuestion surveyHasQuestion : questions){
            QuestionForSurveyDto questionForSurveyDto;
            if(surveyHasQuestion.getQuestion() instanceof MultipleChoiceQuestion){
                questionForSurveyDto = new MultipleChoiceForSurveyDto();
                questionForSurveyDto.setType(QuestionType.MULTIPLE_CHOICE.name());
                List<Choice> choices = choiceRepository.findChoicesByMultipleChoiceQuestionOrderByNum((MultipleChoiceQuestion) surveyHasQuestion.getQuestion());
                ((MultipleChoiceForSurveyDto) questionForSurveyDto).setChoiceDtoList(choiceToChoiceDtoList(choices));
            }
            else if(surveyHasQuestion.getQuestion() instanceof RatingScaleQuestion) {
                questionForSurveyDto = new RatingScaleForSurveyDto();
                questionForSurveyDto.setType(QuestionType.RATING_SCALE.name());
                List<ScaleHasValue> scaleValues = scaleHasValueRepository.findAllByScale((Scale) ((RatingScaleQuestion) surveyHasQuestion.getQuestion()).getScale());
                ((RatingScaleForSurveyDto) questionForSurveyDto).setScaleValueList(scaleValueToScaleValueDtoList(scaleValues));
            }
            else if(surveyHasQuestion.getQuestion() instanceof GradeQuestion) {
                questionForSurveyDto = new GradeForSurveyDto();
                questionForSurveyDto.setType(QuestionType.GRADE.name());

            }
            else{
                questionForSurveyDto = new QuestionForSurveyDto();
                questionForSurveyDto.setType(QuestionType.OPEN_ENDED.name());
            }
            questionForSurveyDto.setSurveyId(survey.getId());
            questionForSurveyDto.setText(surveyHasQuestion.getQuestion().getText());
            questionForSurveyDto.setNumber(surveyHasQuestion.getNumber());

            questionForSurveyDtoList.add(questionForSurveyDto);
        }
        surveyDetailedDto.setQuestionForSurveyDto(questionForSurveyDtoList);

        return surveyDetailedDto;
    }

    private List<ChoiceDto> choiceToChoiceDtoList(List<Choice> choices){

        List<ChoiceDto> choiceDtoList = new ArrayList<>();
        for(Choice choice : choices) {
            ChoiceDto choiceDto = new ChoiceDto();
            choiceDto.setNum(choice.getNum());
            choiceDto.setText(choice.getText());
            choiceDtoList.add(choiceDto);
        }
        return choiceDtoList;
    }

    private List<ScaleValueDto> scaleValueToScaleValueDtoList(List<ScaleHasValue> scaleHasValues){
        List<ScaleValueDto> scaleValueDtoList = new ArrayList<>();
        for(ScaleHasValue scaleValue : scaleHasValues) {
            ScaleValueDto scaleValueDto = new ScaleValueDto();
            scaleValueDto.setValue(scaleValue.getScaleValue().getValue());
            scaleValueDto.setShortDescription(scaleValue.getScaleValue().getShortDescription());
            scaleValueDtoList.add(scaleValueDto);
        }

        return scaleValueDtoList;
    }

    public Survey surveyCreateDtoToSurvey(SurveyCreateDto surveyCreateDto){
        Survey survey = new Survey();
        survey.setTitle(surveyCreateDto.getTitle());
        survey.setDescription(surveyCreateDto.getDescription());
        survey.setLogo(surveyCreateDto.getLogo());
        survey.setUserId(surveyCreateDto.getUserId());
        survey.setPriv(surveyCreateDto.isPriv());
        survey.setActive(surveyCreateDto.isActive());
        survey.setActivationDate(surveyCreateDto.getActivationDate());
        survey.setDeactivationDate(surveyCreateDto.getDeactivationDate());

        return survey;
    }

    public SurveyInfoDto surveyToSurveyInfoDto(Survey survey) {
        return new SurveyInfoDto(survey.getId(), survey.getTitle());
    }

    public SurveyInstance scToInstance(SurveyConnection sc) {
        SurveyInstance si = new SurveyInstance();
        si.setId(sc.getId());
        si.setGroup(sc.getStudentGroup());
        si.setTitle(sc.getSurveyTitle());
        si.setSubject(sc.getSurveySubject());
        si.setFrom("");
        if(sc.getStartTime() != null) {
            si.setFrom(sc.getStartTime().toString());
        }
        si.setTo("");
        if(sc.getEndTime() != null) {
            si.setTo(sc.getEndTime().toString());
        }
        si.setActivated(sc.isActivated());

        return si;
    }
}
