package com.example.sagakafka.model.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {

    private Integer userId;
    private Integer productId;
    private Integer pincode;
}
