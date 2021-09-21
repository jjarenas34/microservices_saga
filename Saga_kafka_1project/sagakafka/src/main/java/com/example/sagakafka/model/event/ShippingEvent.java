package com.example.sagakafka.model.event;

import com.example.sagakafka.model.enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShippingEvent {
    private Integer orderId;
    private Integer pincode;

    private ShippingStatus status;

    public ShippingEvent(Integer orderId) {
        this.orderId = orderId;
    }
}
