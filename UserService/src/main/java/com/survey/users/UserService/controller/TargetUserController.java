package com.survey.users.UserService.controller;

import com.survey.users.UserService.domain.target_user.Connector;
import com.survey.users.UserService.domain.target_user.TargetUser;
import com.survey.users.UserService.dto.for_target_user.*;
import com.survey.users.UserService.dto.message.MessageDto;
import com.survey.users.UserService.service.target_user.FileLoaderService;
import com.survey.users.UserService.service.target_user.TargetUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/target-user")
@AllArgsConstructor
@CrossOrigin
public class TargetUserController {

    private FileLoaderService fileLoader;
    private TargetUserService userService;

    @PostMapping("/upload/users")
    public ResponseEntity<LoadedUsers> uploadUsers(@RequestBody UploadTargetUsers uploadTargetUsers ){
        LoadedUsers res = new LoadedUsers();
        for (MultipartFile file : uploadTargetUsers.getFiles()) {
           LoadedUsers lu = fileLoader.loadUserData(uploadTargetUsers.getOrganizationId(), file, uploadTargetUsers.getConfig(), uploadTargetUsers.getEmailRules(), uploadTargetUsers.isEmailExists(), uploadTargetUsers.isHasHeader());
           res.getSuccess().addAll(lu.getSuccess());
           res.getFail().addAll(lu.getFail());
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TargetUser>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/connectors/{surveyId}")
    public ResponseEntity<List<Connector>> getConnectors(@PathVariable("surveyId") String surveyId, @RequestBody UploadConnectors uploadConnectors){
        List<Connector> res = new ArrayList<>();
        for (MultipartFile file : uploadConnectors.getFiles()) {
            res.addAll(fileLoader.loadConnectors(file, uploadConnectors.getConfig(), surveyId, uploadConnectors.isHasHeader()));
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/connect")
    public ResponseEntity<MessageDto> makeConnections(@RequestBody CreateConnections createConnections){
        return new ResponseEntity<>(userService.makeConnection(createConnections), HttpStatus.OK);
    }

    @GetMapping("/surveys/{email}")
    public ResponseEntity<List<SurveyDto>> getSurveysForUser(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.getSurveysForUser(email), HttpStatus.OK);
    }

    @GetMapping("/surveys/{survey}/instances")
    public ResponseEntity<List<SurveyDto>> getSurveyInstances(@PathVariable("survey") String survey){
        return new ResponseEntity<>(userService.getSurveysInstances(survey), HttpStatus.OK);
    }

    @PostMapping("/activate")
    public ResponseEntity<MessageDto> activateSurvey(@RequestBody ForActivationDto forActivationDto){
        return new ResponseEntity<>(userService.activate(forActivationDto), HttpStatus.OK);
    }
}
