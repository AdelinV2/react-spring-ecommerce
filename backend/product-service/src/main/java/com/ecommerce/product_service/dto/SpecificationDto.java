package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.Specification;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.Specification}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SpecificationDto implements Serializable {

    String title;
    byte orderIndex;

    @Builder.Default
    List<SubSpecificationDto> subSpecifications = new ArrayList<>();

    public static Specification toEntity(SpecificationDto specificationDto) {
        return Specification.builder()
                .title(specificationDto.getTitle())
                .orderIndex(specificationDto.getOrderIndex())
                .subSpecifications(SubSpecificationDto.toEntityList(specificationDto.getSubSpecifications()))
                .build();
    }

    public static SpecificationDto fromEntity(Specification specification) {
        return SpecificationDto.builder()
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
