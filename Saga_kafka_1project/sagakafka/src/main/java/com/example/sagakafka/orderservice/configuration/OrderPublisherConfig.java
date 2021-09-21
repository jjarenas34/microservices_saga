package com.example.sagakafka.orderservice.configuration;

import com.example.sagakafka.model.event.OrderEvent;
import com.example.sagakafka.model.event.ShppingAfterPaymentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderPublisherConfig {

    @Bean
    public Sinks.Many<OrderEvent> orderSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Sinks.Many<ShppingAfterPaymentEvent> shippingAfterSkins(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderEventPublisher(Sinks.Many<OrderEvent> sinks){
        return sinks::asFlux;
    }

    @Bean
    public Supplier<Flux<ShppingAfterPaymentEvent>> shippingEventPublisher(Sinks.Many<ShppingAfterPaymentEvent> sinks){
        return sinks::asFlux;
    }
}
