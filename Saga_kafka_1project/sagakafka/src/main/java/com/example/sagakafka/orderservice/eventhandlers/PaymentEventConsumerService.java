package com.example.sagakafka.orderservice.eventhandlers;

import com.example.sagakafka.model.enums.OrderStatus;
import com.example.sagakafka.model.enums.PaymentStatus;
import com.example.sagakafka.model.event.PaymentEvent;
import com.example.sagakafka.orderservice.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ShippingEventPublisherService seps;

    @Transactional
    public void consumePaymentEvent(PaymentEvent paymentEvent){
        this.purchaseOrderRepository.findById(paymentEvent.getOrderId())
                .ifPresent(purchaseOrder -> {
                    purchaseOrder.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED);
                    this.purchaseOrderRepository.save(purchaseOrder);
                    System.out.println("Order Event Completed !!!");
                    System.out.println("Raise Shipping Event !!!");
                    seps.raiseShippingCreatedEvent(purchaseOrder);
                });

    }
}
