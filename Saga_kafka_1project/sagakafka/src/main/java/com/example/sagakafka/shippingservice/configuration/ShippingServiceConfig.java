package com.example.sagakafka.shippingservice.configuration;

import com.example.sagakafka.model.event.ShippingEvent;
import com.example.sagakafka.model.event.ShppingAfterPaymentEvent;
import com.example.sagakafka.shippingservice.eventhandlers.ShippingEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ShippingServiceConfig {
    @Autowired
    private ShippingEventProcessorService shippingEventProcessorService;

    @Bean
    public Function<ShppingAfterPaymentEvent, ShippingEvent> shippingEventProcessor(){
        return shippingEventProcessorService::processShippedEvent;
    }
}
