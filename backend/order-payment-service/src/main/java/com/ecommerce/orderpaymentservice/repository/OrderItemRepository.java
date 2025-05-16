package com.ecommerce.orderpaymentservice.repository;

import com.ecommerce.orderpaymentservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Long, OrderItem> {

}
