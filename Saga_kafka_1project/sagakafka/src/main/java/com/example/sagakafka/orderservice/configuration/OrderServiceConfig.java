package com.example.sagakafka.orderservice.configuration;

import com.example.sagakafka.model.event.OrderEvent;
import com.example.sagakafka.model.event.PaymentEvent;
import com.example.sagakafka.model.event.ShippingEvent;
import com.example.sagakafka.model.event.ShppingAfterPaymentEvent;
import com.example.sagakafka.orderservice.eventhandlers.PaymentEventConsumerService;
import com.example.sagakafka.orderservice.eventhandlers.ShippingEventConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderServiceConfig {
    //los servicios que consumo
    @Autowired
    private PaymentEventConsumerService consumerService;

    @Autowired
    private ShippingEventConsumerService shippingConsumerService;

    //los eventos que se van a publicar
    @Bean
    public DirectProcessor<OrderEvent> getFlux(){
        return DirectProcessor.create();
    }

    @Bean
    public DirectProcessor<ShppingAfterPaymentEvent> getFlux1(){
        return DirectProcessor.create();
    }

//creo los canales
    @Bean
    public FluxSink<OrderEvent> orderEventChannel(DirectProcessor<OrderEvent> processor){
        return processor.sink();
    }

    @Bean
    public FluxSink<ShppingAfterPaymentEvent> paymentToShipingEventChannel(DirectProcessor<ShppingAfterPaymentEvent> processor){
        return processor.sink();
    }

    //publico en los canales
    @Bean
    public Supplier<Flux<OrderEvent>> orderEventPublisher(DirectProcessor<OrderEvent> processor){
        return () -> processor;
    }

    @Bean
    public Supplier<Flux<ShppingAfterPaymentEvent>> shippingEventPublisher(DirectProcessor<ShppingAfterPaymentEvent> processor){
        return () -> processor;
    }
//consumo los canales
    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return consumerService::consumePaymentEvent;
    }


    @Bean
    public Consumer<ShippingEvent> shippingEventConsumer(){
        return shippingConsumerService::consumeShippingEvent;
    }
}
