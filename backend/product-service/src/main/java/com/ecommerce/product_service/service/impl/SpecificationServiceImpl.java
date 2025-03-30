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


        Specification newSpecification = specificationRepository.save(specification);

        for (SubSpecification subSpecification : specification.getSubSpecifications()) {

            subSpecification.setSpecification(newSpecification);
            subSpecificationService.saveSubSpecification(subSpecification);
        }
    }

    @Override
    public void updateSpecification(Specification specification) {

        specificationRepository.save(specification);

        for (SubSpecification subSpecification : specification.getSubSpecifications()) {
            subSpecificationService.updateSubSpecification(subSpecification);
        }
    }

    @Override
    public void deleteSpecificationById(Integer specificationId) {

        specificationRepository.findById(specificationId).ifPresent(specificationRepository::delete);
    }
}
