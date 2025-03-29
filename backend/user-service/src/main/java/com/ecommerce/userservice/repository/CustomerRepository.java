package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}