package com.ecommerce.cartwishlistservice.repository;

import com.ecommerce.cartwishlistservice.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

    List<CartProduct> findByUserId(String userId);

    void deleteAllByProductId(int productId);
}