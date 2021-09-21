package com.example.sagakafka.orderservice.service;

import com.example.sagakafka.model.dto.OrderRequestDTO;
import com.example.sagakafka.model.dto.OrderResponseDTO;
import com.example.sagakafka.model.enums.OrderStatus;
import com.example.sagakafka.model.enums.ShippingOrderStatus;
import com.example.sagakafka.orderservice.entity.PurchaseOrder;
import com.example.sagakafka.orderservice.eventhandlers.OrderEventPublisherService;
import com.example.sagakafka.orderservice.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    // product price map
    private static final Map<Integer, Integer> PRODUCT_PRICE =  Map.of(
            1, 100,
            2, 200,
            3, 300
    );
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderEventPublisherService eventPublisherService;

    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO){
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO));
        this.eventPublisherService.raiseOrderCreatedEvent(purchaseOrder);
        return purchaseOrder;
    }

    public List<OrderResponseDTO> getAll() {
        return this.purchaseOrderRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private PurchaseOrder dtoToEntity(final OrderRequestDTO dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
        purchaseOrder.setPincode(dto.getPincode());
        purchaseOrder.setShippingStatus(ShippingOrderStatus.SHIPPING_CREATE_ORDER_REQUESTED);
        return purchaseOrder;
    }

    private OrderResponseDTO entityToDto(final PurchaseOrder purchaseOrder){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(purchaseOrder.getId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setPrice(purchaseOrder.getPrice());
        dto.setPincode(purchaseOrder.getPincode());
        dto.setShippingStatus(purchaseOrder.getShippingStatus());
        return dto;
    }
}
