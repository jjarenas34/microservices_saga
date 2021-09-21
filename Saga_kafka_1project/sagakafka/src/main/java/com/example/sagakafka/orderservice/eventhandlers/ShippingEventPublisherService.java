package com.example.sagakafka.orderservice.eventhandlers;

import com.example.sagakafka.model.event.ShppingAfterPaymentEvent;
import com.example.sagakafka.orderservice.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

@Service
public class ShippingEventPublisherService {
    @Autowired
    FluxSink<ShppingAfterPaymentEvent> shippingEventChannel;

    public void raiseShippingCreatedEvent(final PurchaseOrder purchaseOrder) {
        ShppingAfterPaymentEvent sape = new ShppingAfterPaymentEvent();
        sape.setOrderId(purchaseOrder.getId());
        sape.setPincode(purchaseOrder.getPincode());
        sape.setUserId(purchaseOrder.getUserId());
        this.shippingEventChannel.next(sape);
    }
}
