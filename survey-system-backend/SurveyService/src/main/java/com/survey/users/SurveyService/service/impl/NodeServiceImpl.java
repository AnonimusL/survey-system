package com.survey.users.SurveyService.service.impl;
import com.survey.users.SurveyService.domain.*;
import com.survey.users.SurveyService.dto.*;
import com.survey.users.SurveyService.exception.NotFoundException;
import com.survey.users.SurveyService.mapper.NodeMapper;
import com.survey.users.SurveyService.mapper.SurveyMapper;
import com.survey.users.SurveyService.repository.DomainRepository;
import com.survey.users.SurveyService.repository.NodeRepository;
import com.survey.users.SurveyService.repository.SurveyRepository;
import com.survey.users.SurveyService.service.NodeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class NodeServiceImpl implements NodeService {

    private SurveyRepository surveyRepository;
    private SurveyMapper surveyMapper;
    private NodeRepository nodeRepository;
    private DomainRepository domainRepository;
    private NodeMapper nodeMapper;

    @Override
    public NodeDto createNode(NodeCreateDto nodeDto) {
        Node node;
        Domain domain = domainRepository.findByName(nodeDto.getDomain()).orElseThrow(() -> new NotFoundException("Domain not found"));
        Node nodeExist = nodeRepository.findByOrganizationIdAndDomainAndName(nodeDto.getOrganizationId(), domain, nodeDto.getName()).orElse(null);
        System.out.println(nodeDto.getOrganizationId());
        if (nodeExist == null) {
            node = nodeMapper.nodeDtoToNode(nodeDto);
            node = nodeRepository.save(node);
        } else
            node = nodeExist;

        return nodeMapper.nodeToNodeDto(node);
    }

    @Override
    public NodeDto addSurveyOnNode(NodeSurveyDto nodeSurveyDto) {
        Node node = nodeRepository.findById(nodeSurveyDto.getNodeId()).orElseThrow(() -> new NotFoundException("Node not found"));
        Survey survey = surveyRepository.findById(nodeSurveyDto.getSurveyId()).orElseThrow(() -> new NotFoundException("Survey not found"));

        node.setSurvey(survey);
        node = nodeRepository.save(node);

        NodeDto nodeDto = nodeMapper.nodeToNodeDto(node);
        nodeDto.setSurveyInfoDto(surveyMapper.surveyToSurveyInfoDto(survey));
        return nodeDto;
    }

    @Override
    public List<WorkspaceDto> getAllNodes(WorkspaceGetDto workspaceGetDto) {
        Domain domain = domainRepository.findByName(workspaceGetDto.getDomain()).get();
        System.out.println(workspaceGetDto.getUserId() + " "+ workspaceGetDto.getOrganizationId() + " " + workspaceGetDto.getDomain());
        List<Node> nodes = nodeRepository.findWorkspace(workspaceGetDto.getUserId(), workspaceGetDto.getOrganizationId(), domain);
        List<WorkspaceDto> workspaceDtoList = new ArrayList<>();
        for (Node node : nodes) {
            Long parentId = null;
            Long surveyId = null;
            if(node.getParent() != null)
                parentId = node.getParent().getId();
            if(node.getSurvey() != null)
                surveyId = node.getSurvey().getId();
            workspaceDtoList.add(new WorkspaceDto(node.getId(), node.getName(), parentId, surveyId));
        }
        return workspaceDtoList;
    }

    @Override
    public List<NodeDto> getAllNodesDetailed(WorkspaceGetDto workspaceGetDto) {
        Domain domain = domainRepository.findByName(workspaceGetDto.getDomain()).get();
        List<Node> nodes = nodeRepository.findWorkspace(workspaceGetDto.getUserId(), workspaceGetDto.getOrganizationId(), domain);
        List<NodeDto> nodeDtoList = new ArrayList<>();
        for (Node node : nodes) {
            NodeDto nodeDto = nodeMapper.nodeToNodeDto(node);
            if (Objects.equals(node.getOrganizationId(), workspaceGetDto.getOrganizationId()) && node.getSurvey() != null && Objects.equals(node.getSurvey().getOrganizationId(), workspaceGetDto.getOrganizationId()) &&
                    (Objects.equals(node.getSurvey().getUserId(), workspaceGetDto.getUserId()) ||
                            (!Objects.equals(node.getSurvey().getUserId(), workspaceGetDto.getUserId()) && !node.getSurvey().isPriv())))
                nodeDto.setSurveyInfoDto(surveyMapper.surveyToSurveyInfoDto(node.getSurvey()));
            nodeDtoList.add(nodeDto);
        }
        return nodeDtoList;
    }

    @Override
    public List<NodeDto> addNewNodes(String filePath, Long nodeId) {
        List<Node> nodes = addNewFromCsv(filePath, "Education", nodeId);

        List<NodeDto> nodesDto = new ArrayList<>();
        for(Node node : nodes){
            nodesDto.add(nodeMapper.nodeToNodeDto(node));
        }

        return nodesDto;
    }

    public List<Node> addNewFromCsv(String filePath, String domainName, Long parent) {
        try {
            FileReader fileReader = new FileReader(filePath);
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader().build();

            CSVParser parser = new CSVParser(fileReader, csvFormat);

            // Get the headers dynamically
            List<String> headers = parser.getHeaderNames();

            Map<Integer, List<Node>> nodes = new HashMap<>();
            List<Node> nodesRet = new ArrayList<>();
            // Process CSV records
            Node root = nodeRepository.findById(parent).get();
            Domain domain = domainRepository.findByName(domainName).get();

            System.out.println(headers);
            for (CSVRecord record : parser) {
                Node prev = root;
                for (int i = 0; i<headers.size(); i++) {
                    Node node = containsNode(nodes, record.get(headers.get(i)), i);

                    if (node == null) {
                        node = new Node();
                        node.setParent(prev != null ? prev : null);

                        node.setDomain(domain);
                        node.setName(record.get(headers.get(i)));

                        node = nodeRepository.save(node);
                        if(!nodes.containsKey(i))
                            nodes.put(i, new ArrayList<>());
                        nodes.get(i).add(node);
                        nodesRet.add(node);
                    }
                    prev = node;
                }

            }

            return nodesRet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private Node containsNode(Map<Integer, List<Node>> nodes, String name, int place) {
        for(Map.Entry<Integer, List<Node>> entry : nodes.entrySet()){
            for(Node node : entry.getValue()) {
                if (node.getName().equals(name) && entry.getKey() == place) {
                    return node;
                }
            }
        }
        return null;
    }

}
