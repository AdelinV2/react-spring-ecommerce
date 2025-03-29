package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}