package com.ecommerce.orderpaymentservice.repository;

import com.ecommerce.orderpaymentservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Long, Order> {

}
