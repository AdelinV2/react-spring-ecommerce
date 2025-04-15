package com.ecommerce.cartwishlistservice.repository;

import com.ecommerce.cartwishlistservice.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

}