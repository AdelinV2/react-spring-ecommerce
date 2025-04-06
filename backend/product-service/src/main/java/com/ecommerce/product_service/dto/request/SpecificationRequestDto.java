package com.ecommerce.product_service.dto.request;

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
public class SpecificationRequestDto implements Serializable {

    String title;
    byte orderIndex;

    @Builder.Default
    List<SubSpecificationRequestDto> subSpecifications = new ArrayList<>();

    public static Specification toEntity(SpecificationRequestDto specificationRequestDto) {
        return Specification.builder()
                .title(specificationRequestDto.getTitle())
                .orderIndex(specificationRequestDto.getOrderIndex())
                .subSpecifications(SubSpecificationRequestDto.toEntityList(specificationRequestDto.getSubSpecifications()))
                .build();
    }

    public static SpecificationRequestDto fromEntity(Specification specification) {
        return SpecificationRequestDto.builder()
                .title(specification.getTitle())
                .orderIndex(specification.getOrderIndex())
                .subSpecifications(SubSpecificationRequestDto.fromEntityList(specification.getSubSpecifications()))
                .build();
    }

    public static List<SpecificationRequestDto> fromEntityList(List<Specification> specifications) {
        return specifications.stream()
                .map(SpecificationRequestDto::fromEntity)
                .toList();
    }

    public static List<Specification> toEntityList(List<SpecificationRequestDto> specificationRequestDtos) {
        return specificationRequestDtos.stream()
                .map(SpecificationRequestDto::toEntity)
                .toList();
    }
}
