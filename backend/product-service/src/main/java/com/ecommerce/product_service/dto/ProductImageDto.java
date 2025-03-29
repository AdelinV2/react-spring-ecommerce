package com.ecommerce.product_service.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.ecommerce.product_service.entity.ProductImage}
 */
@Value
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProductImageDto implements Serializable {

    byte orderIndex;
    String fileName;
    String fileType;
    String data;
}
