package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.SubSpecification;

import java.util.List;

public interface SubSpecificationService {

    SubSpecification getSubSpecificationById(Integer subSpecificationId);

    List<SubSpecification> getSubSpecificationsBySpecificationId(Integer specificationId);

    void saveSubSpecification(SubSpecification subSpecification);

    void updateSubSpecification(SubSpecification subSpecification);

    void deleteSubSpecification(Integer subSpecificationId);
}
