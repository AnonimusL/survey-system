package com.survey.users.UserService.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.secondExchange.name}")
    private String secondExchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.secondQueue.name}")
    private String secondQueueName;

    @Value("${rabbitmq.secondRouting.key}")
    private String secondRoutingKey;

    @Bean
    public Queue firstQueue(){
        return new Queue(queueName);
    }

    @Bean
    public Queue secondQueue(){
        return new Queue(secondQueueName);
    }

    @Bean
    public TopicExchange firstExchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public TopicExchange secondExchange(){
        return new TopicExchange(secondExchangeName);
    }

    @Bean
    public Binding firstBinding(){
        return BindingBuilder.bind(firstQueue())
                .to(firstExchange())
                .with(routingKey);
    }

    @Bean
    public Binding secondBinding(){
        return BindingBuilder.bind(secondQueue())
                .to(secondExchange())
                .with(secondRoutingKey);
    }
}
