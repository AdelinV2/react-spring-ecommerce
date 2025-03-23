package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.entity.SubSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubSpecificationRepository extends JpaRepository<SubSpecification, Integer> {

    List<SubSpecification> findBySpecificationId(Integer specificationId);
}