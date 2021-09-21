package com.example.sagakafka.model.event;

import lombok.Data;

@Data
public class ShppingAfterPaymentEvent {
    private Integer orderId;
    private Integer userId;
    private Integer pincode;
}
