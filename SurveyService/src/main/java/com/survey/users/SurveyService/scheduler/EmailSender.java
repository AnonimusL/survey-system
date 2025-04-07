package com.survey.users.SurveyService.scheduler;

import com.survey.users.SurveyService.domain.connector.SurveyConnection;
import com.survey.users.SurveyService.producer.RabbitMQProducer;
import com.survey.users.SurveyService.repository.connector.SurveyConnectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.List;

@Component
@AllArgsConstructor
public class EmailSender {

    private SurveyConnectionRepository surveyConnectionRepository;
    private RabbitMQProducer rabbitMQProducer;

    @Scheduled(cron = "0 * * * * *")
    public void executeTask() {
        System.out.println("Task executed at: " + java.time.LocalTime.now());

        long currentTimeMillis = System.currentTimeMillis();
        // Create a Timestamp object representing the current time
        Timestamp currentTimestamp = new Timestamp(currentTimeMillis);

        List<SurveyConnection> surveyConnections = surveyConnectionRepository.findByStartTimeLessThanAndEndTimeGreaterThanAndScheduledIsFalse(currentTimestamp, currentTimestamp);

        System.out.println(surveyConnections);
        System.out.println("Vel " + surveyConnections.size());
        for(SurveyConnection sc : surveyConnections){
            rabbitMQProducer.sendMessage(sc.getStudentGroup());
            sc.setScheduled(true);
        }
        if(surveyConnections.size() > 0) {
            surveyConnectionRepository.saveAll(surveyConnections);
        }

    }
}
