package com.survey.users.SurveyService.controller;

import com.survey.users.SurveyService.domain.connector.Connector;
import com.survey.users.SurveyService.service.dataret.FileLoader;
import com.survey.users.SurveyService.service.dataret.SurveyQuestionsDataRet;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dataret")
@AllArgsConstructor
public class DataRetController {

    private SurveyQuestionsDataRet service;
    private FileLoader fileLoader;

    @GetMapping("/connectors")
    public ResponseEntity<List<Connector>> getConnectors(){
        return new ResponseEntity<>(fileLoader.loadApache("src\\main\\resources\\static\\schedule.csv", "src\\main\\resources\\static\\config.txt"), HttpStatus.OK);
    }

}
