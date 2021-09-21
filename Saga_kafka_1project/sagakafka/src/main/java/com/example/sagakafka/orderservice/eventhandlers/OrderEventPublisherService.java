package com.example.sagakafka.orderservice.eventhandlers;

import com.example.sagakafka.model.event.OrderEvent;
import com.example.sagakafka.orderservice.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

@Service
public class OrderEventPublisherService {
    @Autowired
    private FluxSink<OrderEvent> orderEventChannel;


    public void raiseOrderCreatedEvent(final PurchaseOrder purchaseOrder){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setUserId(purchaseOrder.getUserId());
        orderEvent.setPrice(purchaseOrder.getPrice());
        orderEvent.setOrderId(purchaseOrder.getId());
        //orderEvent.setPincode(purchaseOrder.getPincode());
        this.orderEventChannel.next(orderEvent);
    }
}
