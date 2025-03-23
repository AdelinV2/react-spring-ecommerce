package com.ecommerce.product_service.service;


import com.ecommerce.product_service.entity.Specification;

import java.util.List;

public interface SpecificationService {

    Specification getSpecificationById(Integer specificationId);

    List<Specification> getSpecificationsByProductId(Integer productId);

    void saveSpecification(Specification specification);

    void updateSpecification(Specification specification);

    void deleteSpecificationById(Integer specificationId);
}
