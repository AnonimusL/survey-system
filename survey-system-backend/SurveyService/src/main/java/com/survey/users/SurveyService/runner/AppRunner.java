package com.survey.users.SurveyService.runner;

import com.survey.users.SurveyService.domain.*;
import com.survey.users.SurveyService.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppRunner implements CommandLineRunner {

    private DomainRepository domainRepository;
    private ScaleRepository scaleRepository;
    private ScaleHasValueRepository scaleHasValueRepository;
    private ScaleValueRepository scaleValueRepository;
    private NodeRepository nodeRepository;

    @Override
    public void run(String... args) throws Exception {
        Domain domain = new Domain();
        domain.setName("Education");
        domain.setDescription("Domain description");
        domain.setOrganizationId(1L);
        domain.setUserId(-1L);
        domain.setPriv(false);

        domain = domainRepository.save(domain);

        Scale scale = new Scale();
        scale.setName("Scale example");
        scale.setOrganizationId(1L);

        scale = scaleRepository.save(scale);

        ScaleValue scaleValue = new ScaleValue();
        scaleValue.setValue(5);
        scaleValue.setShortDescription("Super");
        scaleValue.setDescription("Desc");

        scaleValue = scaleValueRepository.save(scaleValue);

        ScaleValue scaleValue2 = new ScaleValue();
        scaleValue2.setValue(3);
        scaleValue2.setShortDescription("OK");
        scaleValue2.setDescription("Desc");

        scaleValue2 = scaleValueRepository.save(scaleValue2);

        ScaleValue scaleValue3 = new ScaleValue();
        scaleValue3.setValue(1);
        scaleValue3.setShortDescription("Not OK");
        scaleValue3.setDescription("Desc");

        scaleValue3 = scaleValueRepository.save(scaleValue3);

        ScaleHasValue scaleHasValue = new ScaleHasValue();
        scaleHasValue.setScale(scale);
        scaleHasValue.setScaleValue(scaleValue);
        scaleHasValueRepository.save(scaleHasValue);

        ScaleHasValue scaleHasValue2 = new ScaleHasValue();
        scaleHasValue.setScale(scale);
        scaleHasValue.setScaleValue(scaleValue2);
        scaleHasValueRepository.save(scaleHasValue2);

        ScaleHasValue scaleHasValue3 = new ScaleHasValue();
        scaleHasValue.setScale(scale);
        scaleHasValue.setScaleValue(scaleValue3);
        scaleHasValueRepository.save(scaleHasValue3);

        Node node = new Node();
        node.setDomain(domain);
        node.setParent(null);
        node.setSurvey(null);
        node.setUserId(1L);
        node.setOrganizationId(1L);
        node.setName("RAF");

        nodeRepository.save(node);
    }
}
