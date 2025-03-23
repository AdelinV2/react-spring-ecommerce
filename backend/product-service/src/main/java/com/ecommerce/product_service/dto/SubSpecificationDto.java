package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.SubSpecification;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.SubSpecification}
 */
@Value
@Getter
@Setter
@Builder
@AllArgsConstructor
public class SubSpecificationDto implements Serializable {

    int id;
    int specificationId;
    String description;
    String value;
    byte orderIndex;

    public static SubSpecification toEntity(SubSpecificationDto subSpecificationDto) {

        return SubSpecification.builder()
                .id(subSpecificationDto.getId())
                .description(subSpecificationDto.getDescription())
                .value(subSpecificationDto.getValue())
                .orderIndex(subSpecificationDto.getOrderIndex())
                .build();
    }

    public static SubSpecificationDto fromEntity(SubSpecification subSpecification) {

        return SubSpecificationDto.builder()
                .id(subSpecification.getId())
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