package com.survey.users.SurveyService.mapper;

import com.survey.users.SurveyService.domain.*;
import com.survey.users.SurveyService.dto.*;
import com.survey.users.SurveyService.repository.ChoiceRepository;
import com.survey.users.SurveyService.repository.ScaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionMapper {

    private ChoiceRepository choiceRepository;
    private ScaleRepository scaleRepository;

    public Question questionCreateDtoToQuestion(QuestionForSurveyDto questionsForSurveyDto) {
        Question question;
        if(questionsForSurveyDto instanceof MultipleChoiceForSurveyDto){
            question = new MultipleChoiceQuestion();
            for(ChoiceDto choiceDto : ((MultipleChoiceForSurveyDto) questionsForSurveyDto).getChoiceDtoList()){
                Choice choice = new Choice();
                choice.setMultipleChoiceQuestion((MultipleChoiceQuestion) question);
                choice.setNum(choiceDto.getNum());
                choice.setText(choiceDto.getText());
                choiceRepository.save(choice);
            }
        }
        else if(questionsForSurveyDto instanceof RatingScaleForSurveyDto){
            question = new RatingScaleQuestion();
            Scale scale = scaleRepository.findById(((RatingScaleForSurveyDto) questionsForSurveyDto).getScaleId()).get();
            ((RatingScaleQuestion) question).setScale(scale);
        }
        else if(questionsForSurveyDto instanceof GradeForSurveyDto){
            question = new GradeQuestion();
        }
        else{
            question = new OpenEndedQuestion();
        }

        question.setText(questionsForSurveyDto.getText());

        return question;
    }
}
