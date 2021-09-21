package com.example.sagakafka.model.event;

import lombok.Data;

@Data
public class OrderEvent {
    private Integer orderId;
    private Integer userId;
    private Integer price;
}
