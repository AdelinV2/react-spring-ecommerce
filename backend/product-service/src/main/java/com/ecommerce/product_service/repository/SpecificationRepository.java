package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationRepository extends JpaRepository<Specification, Integer> {

    List<Specification> findByProductId(Integer productId);
}