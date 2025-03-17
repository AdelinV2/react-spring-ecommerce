package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}