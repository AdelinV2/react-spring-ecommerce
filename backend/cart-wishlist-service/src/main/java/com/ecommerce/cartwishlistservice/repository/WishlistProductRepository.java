package com.ecommerce.cartwishlistservice.repository;

import com.ecommerce.cartwishlistservice.entity.WishlistProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistProductRepository extends JpaRepository<WishlistProduct, Integer> {

}