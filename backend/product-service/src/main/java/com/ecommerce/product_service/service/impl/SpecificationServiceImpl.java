package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.entity.Specification;
import com.ecommerce.product_service.entity.SubSpecification;
import com.ecommerce.product_service.repository.SpecificationRepository;
import com.ecommerce.product_service.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;
    private final SubSpecificationServiceImpl subSpecificationService;

    @Override
    public Specification getSpecificationById(Integer specificationId) {
        return specificationRepository.findById(specificationId).orElse(null);
    }

    @Override
    public List<Specification> getSpecificationsByProductId(Integer productId) {
        return specificationRepository.findByProductId(productId);
    }

    @Override
    public void saveSpecification(Specification specification) {

        for (SubSpecification subSpecification : specification.getSubSpecifications()) {
            subSpecificationService.saveSubSpecification(subSpecification);
        }

        specificationRepository.save(specification);
    }

    @Override
    public void updateSpecification(Specification specification) {

        for (SubSpecification subSpecification : specification.getSubSpecifications()) {
            subSpecificationService.updateSubSpecification(subSpecification);
        }

        specificationRepository.save(specification);
    }

    @Override
    public void deleteSpecificationById(Integer specificationId) {

        Specification specification = specificationRepository.findById(specificationId).orElse(null);

        if (specification != null) {
            specificationRepository.delete(specification);
        }
    }
}
