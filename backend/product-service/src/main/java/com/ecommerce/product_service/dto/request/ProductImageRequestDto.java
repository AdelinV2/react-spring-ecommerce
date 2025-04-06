package com.ecommerce.product_service.dto.request;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.ecommerce.product_service.entity.ProductImage}
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageRequestDto implements Serializable {

    byte orderIndex;
    String fileName;
    String fileType;
    String data;
}
