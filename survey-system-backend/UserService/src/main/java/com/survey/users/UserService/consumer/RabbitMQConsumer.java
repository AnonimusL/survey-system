package com.survey.users.UserService.consumer;

import com.survey.users.UserService.service.target_user.TargetUserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQConsumer {

    private TargetUserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbitmq.secondQueue.name}")
    public void consume(String message){
        LOGGER.info("Received message " + message);
        //userService.sendEmail(message);
    }
}
