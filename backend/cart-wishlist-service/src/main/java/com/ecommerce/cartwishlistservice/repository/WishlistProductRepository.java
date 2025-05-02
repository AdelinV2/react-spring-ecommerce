package com.ecommerce.cartwishlistservice.repository;

import com.ecommerce.cartwishlistservice.entity.WishlistProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistProductRepository extends JpaRepository<WishlistProduct, Integer> {

    void deleteAllByProductId(int productId);

    List<WishlistProduct> findByUserId(String userId);
}