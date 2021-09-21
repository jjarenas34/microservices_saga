package com.example.sagakafka.model.dto;

import com.example.sagakafka.model.enums.OrderStatus;
import com.example.sagakafka.model.enums.ShippingOrderStatus;
import lombok.Data;

@Data
public class OrderResponseDTO {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;
    private Integer pincode;
    private ShippingOrderStatus shippingStatus;
}
