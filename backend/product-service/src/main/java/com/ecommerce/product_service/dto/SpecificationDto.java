package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.Specification;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.Specification}
 */
@Value
@Builder
public class SpecificationDto implements Serializable {

    int id;
    int productId;
    String title;
    byte orderIndex;
    List<SubSpecificationDto> subSpecifications;

    public static Specification toEntity(SpecificationDto specificationDto) {

        return Specification.builder()
                .id(specificationDto.getId())
                .title(specificationDto.getTitle())
                .orderIndex(specificationDto.getOrderIndex())
                .build();
    }

    public static SpecificationDto fromEntity(com.ecommerce.product_service.entity.Specification specification) {

        return SpecificationDto.builder()
                .id(specification.getId())
                .productId(specification.getProduct().getId())
                .title(specification.getTitle())
                .orderIndex(specification.getOrderIndex())
                .subSpecifications(SubSpecificationDto.fromEntityList(specification.getSubSpecifications()))
                .build();
    }

    public static List<SpecificationDto> fromEntityList(List<Specification> specifications) {

        return specifications.stream()
                .map(SpecificationDto::fromEntity)
                .toList();
    }

    public static List<Specification> toEntityList(List<SpecificationDto> specificationDtos) {

        return specificationDtos.stream()
                .map(SpecificationDto::toEntity)
                .toList();
    }
}