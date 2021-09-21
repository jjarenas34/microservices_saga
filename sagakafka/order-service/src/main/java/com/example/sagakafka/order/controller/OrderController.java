package com.example.sagakafka.order.controller;

import com.example.sagakafka.commons.dto.OrderRequestDto;
import com.example.sagakafka.order.entity.PurchaseOrder;
import com.example.sagakafka.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public List<PurchaseOrder> getOrders(){
        return orderService.getAllOrders();
    }
}
