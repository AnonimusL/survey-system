package com.survey.users.SurveyService.service.dataret;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.survey.users.SurveyService.domain.*;
import com.survey.users.SurveyService.dto.dataret.LoadedSurveyData;
import com.survey.users.SurveyService.exception.NotFoundException;
import com.survey.users.SurveyService.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CsvService {

    private DomainRepository domainRepository;
    private NodeRepository nodeRepository;
    private SurveyRepository surveyRepository;
    private ChoiceRepository choiceRepository;
    private QuestionRepository questionRepository;
    private SurveyHasQuestionRepository surveyHasQuestionRepository;

    public List<LoadedSurveyData> readCsvFile(String filePath, String domainName, Long idNodeParent) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            CsvToBean<LoadedSurveyData> csvToBean = new CsvToBeanBuilder<LoadedSurveyData>(reader)
                    .withType(LoadedSurveyData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Long organizationId = 1L;
            Long userId = 1L;

            List<LoadedSurveyData> data = csvToBean.parse();
            Domain domain = domainRepository.findByName(domainName).orElseThrow(()->new NotFoundException(String.format("Domain with name %s not found", domainName)));
            String type = data.get(0).getTip();

            Node parent = nodeRepository.findById(idNodeParent).get();
            Node node = new Node();
            node.setName(type.toUpperCase());
            node.setDomain(domain);
            node.setParent(parent);

            Survey survey = new Survey();
            survey.setOrganizationId(organizationId);
            survey.setUserId(userId);
            survey.setDescription(type.toUpperCase());
            survey.setTitle(type.toUpperCase());
            survey = surveyRepository.save(survey);
            node.setSurvey(survey);
            node.setOrganizationId(organizationId);
            node.setUserId(userId);
            node = nodeRepository.save(node);

            int qNum = 1;
            for(LoadedSurveyData d : data){
                if(!d.getTip().equals(type)){
                    type = d.getTip();
                    node = new Node();
                    node.setName(type.toUpperCase());
                    node.setDomain(domain);

                    survey = new Survey();
                    survey.setDescription(type.toUpperCase());
                    survey.setTitle(type.toUpperCase());
                    survey.setOrganizationId(organizationId);
                    survey.setUserId(userId);
                    survey = surveyRepository.save(survey);
                    node.setSurvey(survey);
                    node.setOrganizationId(organizationId);
                    node.setUserId(userId);
                    node.setParent(parent);
                    node = nodeRepository.save(node);

                    qNum = 1;
                }
                Question q;
                if(d.getFormat().equals("ocena")){
                    q = new GradeQuestion();
                    q.setText(d.getTekst());
                    q = questionRepository.save(q);
                }
                else if(d.getFormat().equals("tekst")){
                    q = new OpenEndedQuestion();
                    q.setText(d.getTekst());
                    q = questionRepository.save(q);
                }
                else {
                    q = new MultipleChoiceQuestion();
                    q.setText(d.getTekst());
                    q = questionRepository.save(q);
                    String choices[] = d.getFormat().split("\\|");
                    for(int i = 0; i<choices.length; i++){
                        Choice choice = new Choice();
                        choice.setText(choices[i]);
                        choice.setNum(i+1);
                        choice.setMultipleChoiceQuestion((MultipleChoiceQuestion) q);
                        choice = choiceRepository.save(choice);
                    }
                }

                SurveyHasQuestion surveyHasQuestion = new SurveyHasQuestion();
                surveyHasQuestion.setQuestion(q);
                surveyHasQuestion.setSurvey(survey);
                surveyHasQuestion.setNumber(qNum);
                surveyHasQuestionRepository.save(surveyHasQuestion);

                qNum++;

            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> readCsvFile2(MultipartFile file, String domainName, Long idNodeParent) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<LoadedSurveyData> csvToBean = new CsvToBeanBuilder<LoadedSurveyData>(csvReader)
                    .withType(LoadedSurveyData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Long organizationId = 1L;
            Long userId = 1L;

            List<LoadedSurveyData> data = csvToBean.parse();
            Domain domain = domainRepository.findByName(domainName).orElseThrow(()->new NotFoundException(String.format("Domain with name %s not found", domainName)));
            String type = data.get(0).getTip();

            Node parent = nodeRepository.findById(idNodeParent).get();
            List<Long> surveysIds = new ArrayList<>();

            Node node = new Node();
            node.setName(type.toUpperCase());
            node.setDomain(domain);
            node.setParent(parent);

            Survey survey = new Survey();
            survey.setOrganizationId(organizationId);
            survey.setUserId(userId);
            survey.setDescription(type.toUpperCase());
            survey.setTitle(type.toUpperCase());
            survey = surveyRepository.save(survey);
            surveysIds.add(survey.getId());

            node.setSurvey(survey);
            node.setOrganizationId(organizationId);
            node.setUserId(userId);
            node = nodeRepository.save(node);

            int qNum = 1;
            for(LoadedSurveyData d : data){
                if(!d.getTip().equals(type)){
                    type = d.getTip();
                    node = new Node();
                    node.setName(type.toUpperCase());
                    node.setDomain(domain);

                    survey = new Survey();
                    survey.setDescription(type.toUpperCase());
                    survey.setTitle(type.toUpperCase());
                    survey.setOrganizationId(organizationId);
                    survey.setUserId(userId);
                    survey = surveyRepository.save(survey);
                    surveysIds.add(survey.getId());
                    node.setSurvey(survey);
                    node.setOrganizationId(organizationId);
                    node.setUserId(userId);
                    node.setParent(parent);
                    node = nodeRepository.save(node);

                    qNum = 1;
                }
                Question q;
                if(d.getFormat().equals("ocena")){
                    q = new GradeQuestion();
                    q.setText(d.getTekst());
                    q = questionRepository.save(q);
                }
                else if(d.getFormat().equals("tekst")){
                    q = new OpenEndedQuestion();
                    q.setText(d.getTekst());
                    q = questionRepository.save(q);
                }
                else {
                    q = new MultipleChoiceQuestion();
                    q.setText(d.getTekst());
                    q = questionRepository.save(q);
                    String choices[] = d.getFormat().split("\\|");
                    for(int i = 0; i<choices.length; i++){
                        Choice choice = new Choice();
                        choice.setText(choices[i]);
                        choice.setNum(i+1);
                        choice.setMultipleChoiceQuestion((MultipleChoiceQuestion) q);
                        choice = choiceRepository.save(choice);
                    }
                }

                SurveyHasQuestion surveyHasQuestion = new SurveyHasQuestion();
                surveyHasQuestion.setQuestion(q);
                surveyHasQuestion.setSurvey(survey);
                surveyHasQuestion.setNumber(qNum);
                surveyHasQuestionRepository.save(surveyHasQuestion);

                qNum++;

            }
            return surveysIds;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
