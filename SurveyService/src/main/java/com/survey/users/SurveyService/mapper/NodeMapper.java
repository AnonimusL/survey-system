package com.survey.users.SurveyService.mapper;

import com.survey.users.SurveyService.domain.Domain;
import com.survey.users.SurveyService.domain.Node;
import com.survey.users.SurveyService.dto.NodeCreateDto;
import com.survey.users.SurveyService.dto.NodeDto;
import com.survey.users.SurveyService.repository.DomainRepository;
import com.survey.users.SurveyService.repository.NodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NodeMapper {

    private DomainRepository domainRepository;
    private NodeRepository nodeRepository;

    public Node nodeDtoToNode(NodeCreateDto nodeDto) {
        Node node = new Node();
        node.setName(nodeDto.getName());
        Domain domain = domainRepository.findByName(nodeDto.getDomain()).orElse(null);
        node.setDomain(domain);
        Node parent = nodeRepository.findById(nodeDto.getIdParent()).orElse(null);
        node.setParent(parent);
        node.setUserId(nodeDto.getUserId());
        node.setOrganizationId(nodeDto.getOrganizationId());
        node.setPriv(node.isPriv());

        return node;
    }

    public NodeDto nodeToNodeDto(Node node) {
        NodeDto nodeDto = new NodeDto();
        nodeDto.setName(node.getName());
        nodeDto.setDomain(node.getDomain().getName());
        if(node.getParent() != null)
            nodeDto.setIdParent(node.getParent().getId());
        nodeDto.setOrganizationId(node.getOrganizationId());
        nodeDto.setUserId(node.getUserId());
        nodeDto.setId(node.getId());

        return nodeDto;
    }
}
