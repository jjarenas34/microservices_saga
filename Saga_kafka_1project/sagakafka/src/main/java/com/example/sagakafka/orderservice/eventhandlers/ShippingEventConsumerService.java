package com.example.sagakafka.orderservice.eventhandlers;

import com.example.sagakafka.model.enums.OrderStatus;
import com.example.sagakafka.model.enums.ShippingOrderStatus;
import com.example.sagakafka.model.enums.ShippingStatus;
import com.example.sagakafka.model.event.ShippingEvent;
import com.example.sagakafka.orderservice.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShippingEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public void consumeShippingEvent(ShippingEvent shippingEvent){
        System.out.println("********* Code inside consumeShippingEvent with ShippingEvent"+ shippingEvent);
        System.out.println("********* Code inside consumeShippingEvent with purchaseOrder"+ purchaseOrderRepository.findById(shippingEvent.getOrderId()));

        this.purchaseOrderRepository.findById(shippingEvent.getOrderId())
                .ifPresent(purchaseOrder -> {
                    if(purchaseOrder.getStatus().equals(OrderStatus.ORDER_COMPLETED)){
                        purchaseOrder.setShippingStatus(shippingEvent.getStatus().equals(ShippingStatus.NOT_SUPPORTED) ? ShippingOrderStatus.ORDER_NOT_SHIPPABLE: ShippingOrderStatus.ORDER_SHIPPABLE);
                        System.out.println("Final Print    "+purchaseOrder);
                        this.purchaseOrderRepository.save(purchaseOrder);
                    }else {
                        purchaseOrder.setShippingStatus(ShippingOrderStatus.ORDER_NOT_COMPLETE_THUS_NOT_SHIPPABLE);
                    }
                });
    }
}
