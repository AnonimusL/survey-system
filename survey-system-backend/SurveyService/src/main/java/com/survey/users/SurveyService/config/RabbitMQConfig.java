package com.survey.users.SurveyService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.secondExchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.secondQueue.name}")
    private String secondQueueName;

    @Value("${rabbitmq.secondRouting.key}")
    private String secondRoutingKey;

    @Bean
    public Queue secondQueue(){
        return new Queue(secondQueueName);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding secondBinding(){
        return BindingBuilder.bind(secondQueue())
                .to(exchange())
                .with(secondRoutingKey);
    }
}
