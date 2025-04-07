package com.survey.users.SurveyService.service;

import com.survey.users.SurveyService.dto.*;

import java.util.List;

public interface NodeService {

    NodeDto createNode(NodeCreateDto nodeDto);

    NodeDto addSurveyOnNode(NodeSurveyDto nodeDto);

    List<WorkspaceDto> getAllNodes(WorkspaceGetDto workspaceGetDto);

    List<NodeDto> addNewNodes(String filePath, Long nodeId);

    List<NodeDto> getAllNodesDetailed(WorkspaceGetDto workspaceGetDto);
}
