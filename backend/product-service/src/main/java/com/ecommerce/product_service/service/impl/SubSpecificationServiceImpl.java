package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.entity.SubSpecification;
import com.ecommerce.product_service.repository.SubSpecificationRepository;
import com.ecommerce.product_service.service.SubSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubSpecificationServiceImpl implements SubSpecificationService {

    private final SubSpecificationRepository subSpecificationRepository;
    private final SpecificationServiceImpl specificationService;

    @Override
    public SubSpecification getSubSpecificationById(Integer subSpecificationId) {
        return subSpecificationRepository.findById(subSpecificationId).orElse(null);
    }

    @Override
    public List<SubSpecification> getSubSpecificationsBySpecificationId(Integer specificationId) {
        return subSpecificationRepository.findBySpecificationId(specificationId);
    }

    @Override
    public void saveSubSpecification(SubSpecification subSpecification) {
        subSpecificationRepository.save(subSpecification);
    }

    @Override
    public void updateSubSpecification(SubSpecification subSpecification) {
        subSpecificationRepository.save(subSpecification);
    }

    @Override
    public void deleteSubSpecification(Integer subSpecificationId) {

        SubSpecification subSpecification = subSpecificationRepository.findById(subSpecificationId).orElse(null);

        if (subSpecification == null) {
            return;
        }

        subSpecificationRepository.delete(subSpecification);
    }
}
