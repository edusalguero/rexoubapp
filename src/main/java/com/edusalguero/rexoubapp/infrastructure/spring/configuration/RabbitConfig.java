package com.edusalguero.rexoubapp.infrastructure.spring.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rexoubapp.domain.events.notifications-queue-name}")
    private String domainEventsQueueName;
    @Value("${rexoubapp.domain.events.exchange-name}")
    private String exchangeName;

    @Bean
    Queue domainEventsQueue() {
        return new Queue(domainEventsQueueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding bindingDomainEvents(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(domainEventsQueueName);
    }

}
