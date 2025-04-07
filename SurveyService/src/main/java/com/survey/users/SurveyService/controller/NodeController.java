package com.survey.users.SurveyService.controller;

import com.survey.users.SurveyService.dto.*;
import com.survey.users.SurveyService.dto.dataret.NewNodeDto;
import com.survey.users.SurveyService.service.NodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspace")
@AllArgsConstructor
@CrossOrigin
public class NodeController {

    private NodeService nodeService;

    @PostMapping("/node")
    public ResponseEntity<NodeDto> addNode(@RequestBody NodeCreateDto nodeDto){
        return new ResponseEntity<>(nodeService.createNode(nodeDto), HttpStatus.OK);
    }

    @PostMapping("/node/survey")
    public ResponseEntity<NodeDto> addNodeSurvey(@RequestBody NodeSurveyDto nodeDto){
        return new ResponseEntity<>(nodeService.addSurveyOnNode(nodeDto), HttpStatus.OK);
    }

    @PostMapping("/workspace/detailed")
    public ResponseEntity<List<NodeDto>> getWorkspaceDetailed(@RequestBody WorkspaceGetDto workspaceGetDto){
        return new ResponseEntity<>(nodeService.getAllNodesDetailed(workspaceGetDto), HttpStatus.OK);
    }

    @PostMapping("/workspace")
    public ResponseEntity<List<WorkspaceDto>> getWorkspace(@RequestBody WorkspaceGetDto workspaceGetDto){
        return new ResponseEntity<>(nodeService.getAllNodes(workspaceGetDto), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<List<NodeDto>> addNewNodes(@RequestBody NewNodeDto nodeDto){
        return new ResponseEntity<>(nodeService.addNewNodes(nodeDto.getPath(), nodeDto.getId()), HttpStatus.OK);
    }
}
