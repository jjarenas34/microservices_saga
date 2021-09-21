package com.example.sagakafka.paymentservice.configuration;

import com.example.sagakafka.model.event.OrderEvent;
import com.example.sagakafka.model.event.PaymentEvent;
import com.example.sagakafka.paymentservice.eventhandlers.OrderEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class PaymentServiceConfig {

    @Autowired
    private OrderEventProcessorService orderEventProcessorService;

    @Bean
    public Function<OrderEvent, PaymentEvent> orderEventProcessor(){
        return orderEventProcessorService::processOrderEvent;
    }
}
