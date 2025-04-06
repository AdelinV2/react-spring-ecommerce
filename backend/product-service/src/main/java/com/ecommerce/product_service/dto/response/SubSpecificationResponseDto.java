package com.ecommerce.product_service.dto.response;

import com.ecommerce.product_service.entity.SubSpecification;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.SubSpecification}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubSpecificationResponseDto implements Serializable {

    int id;
    String description;
    String value;
    byte orderIndex;

    public static List<SubSpecificationResponseDto> fromEntities(List<SubSpecification> subSpecifications) {

        return subSpecifications.stream()
                .map(subSpecification -> new SubSpecificationResponseDto(
                        subSpecification.getId(),
                        subSpecification.getDescription(),
                        subSpecification.getValue(),
                        subSpecification.getOrderIndex()))
                .toList();
    }
}