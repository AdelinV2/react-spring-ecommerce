package com.ecommerce.product_service.dto.request;

import com.ecommerce.product_service.entity.SubSpecification;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.SubSpecification}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubSpecificationRequestDto implements Serializable {

    String description;
    String value;
    byte orderIndex;

    public static SubSpecification toEntity(SubSpecificationRequestDto subSpecificationRequestDto) {

        return SubSpecification.builder()
                .description(subSpecificationRequestDto.getDescription())
                .value(subSpecificationRequestDto.getValue())
                .orderIndex(subSpecificationRequestDto.getOrderIndex())
                .build();
    }

    public static SubSpecificationRequestDto fromEntity(SubSpecification subSpecification) {

        return SubSpecificationRequestDto.builder()
                .description(subSpecification.getDescription())
                .value(subSpecification.getValue())
                .orderIndex(subSpecification.getOrderIndex())
                .build();
    }

    public static List<SubSpecificationRequestDto> fromEntityList(List<SubSpecification> subSpecifications) {

        return subSpecifications.stream()
                .map(SubSpecificationRequestDto::fromEntity)
                .toList();
    }

    public static List<SubSpecification> toEntityList(List<SubSpecificationRequestDto> subSpecificationRequestDtos) {

        return subSpecificationRequestDtos.stream()
                .map(SubSpecificationRequestDto::toEntity)
                .toList();
    }
}
