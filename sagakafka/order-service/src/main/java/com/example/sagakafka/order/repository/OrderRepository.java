package com.example.sagakafka.order.repository;

import com.example.sagakafka.order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrder,Integer> {
}
