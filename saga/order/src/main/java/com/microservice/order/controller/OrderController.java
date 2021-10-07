package com.microservice.order.controller;


import com.microservice.order.entity.PurchaseOrder;
import com.microservice.order.service.OrderService;
import com.util.commons.dto.OrderRequestDto;
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
