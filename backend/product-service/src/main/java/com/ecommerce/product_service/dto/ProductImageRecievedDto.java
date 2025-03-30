package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.ProductImage;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.ecommerce.product_service.entity.ProductImage}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageRecievedDto implements Serializable {

    int productId;
    byte orderIndex;
    String imageUrl;

    public static ProductImage toEntity(ProductImageRecievedDto productImage) {
        return ProductImage.builder()
                .orderIndex(productImage.getOrderIndex())
                .imageUrl(productImage.getImageUrl())
                .build();
    }
}