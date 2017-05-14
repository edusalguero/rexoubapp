package com.edusalguero.rexoubador.infraestructure.spring.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rexoubador.domain.events.notifications-queue-name}")
    private String domainEventsQueueName;
    @Value("${rexoubador.domain.events.exchange-name}")
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
