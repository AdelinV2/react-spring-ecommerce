package com.ecommerce.product_service.dto.response;

import com.ecommerce.product_service.entity.Specification;
import com.ecommerce.product_service.entity.SubSpecification;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.Specification}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecificationResponseDto implements Serializable {

    int id;
    String title;
    byte orderIndex;
    List<SubSpecificationResponseDto> subSpecifications;

    public static List<SpecificationResponseDto> fromEntities(List<Specification> specifications) {

        return specifications.stream()
                .map(specification -> new SpecificationResponseDto(
                        specification.getId(),
                        specification.getTitle(),
                        specification.getOrderIndex(),
                        SubSpecificationResponseDto.fromEntities(specification.getSubSpecifications())))
                .toList();
    }
}