package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.SubSpecification;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.SubSpecification}
 */
@Value
@Builder
public class SubSpecificationDto implements Serializable {

    String description;
    String value;
    byte orderIndex;

    public static SubSpecification toEntity(SubSpecificationDto subSpecificationDto) {

        return SubSpecification.builder()
                .description(subSpecificationDto.getDescription())
                .value(subSpecificationDto.getValue())
                .orderIndex(subSpecificationDto.getOrderIndex())
                .build();
    }

    public static SubSpecificationDto fromEntity(SubSpecification subSpecification) {

        return SubSpecificationDto.builder()
                .description(subSpecification.getDescription())
                .value(subSpecification.getValue())
                .orderIndex(subSpecification.getOrderIndex())
                .build();
    }

    public static List<SubSpecificationDto> fromEntityList(List<SubSpecification> subSpecifications) {

        return subSpecifications.stream()
                .map(SubSpecificationDto::fromEntity)
                .toList();
    }

    public static List<SubSpecification> toEntityList(List<SubSpecificationDto> subSpecificationDtos) {

        return subSpecificationDtos.stream()
                .map(SubSpecificationDto::toEntity)
                .toList();
    }
}
