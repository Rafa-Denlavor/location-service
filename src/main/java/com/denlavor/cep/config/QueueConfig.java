package com.denlavor.cep.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class QueueConfig {
    // Declaração da fila
    @Bean("district-queue")
    public Queue districtQueue() {
        return QueueBuilder.durable("district-queue")
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "district-dlq-queue")
                .build();
    }
    @Bean("district-dlq-queue")
    public Queue districtDlqQueue() {
        return QueueBuilder.durable("district-dlq-queue").build();
    }


        // Declaração do exchange
        @Bean("direct-queue")
    public DirectExchange districtExchange() {
        return new DirectExchange("");
    }

    // plug entre a fila e o exchange
    @Bean("binding-queue")
    public Binding districtBinding(Queue district, DirectExchange exchange) {
        return BindingBuilder.bind(district).to(exchange).with("district-queue");
    }

    // plug entre a fila e o exchange
    @Bean("binding-dql-queue")
    public Binding districtDlqBinding(Queue districtDql, DirectExchange exchange) {
        return BindingBuilder.bind(districtDql).to(exchange).with("district-dlq-queue");
    }
}
