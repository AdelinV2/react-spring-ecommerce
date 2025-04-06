package com.ecommerce.product_service.dto.response;

import com.ecommerce.product_service.entity.ProductImage;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.ProductImage}
 */
@Value
public class ProductImageResponseDto implements Serializable {

    int id;
    byte orderIndex;
    String imageUrl;

    public static List<ProductImageResponseDto> fromEntities(List<ProductImage> images) {
        return images.stream()
                .map(image -> new ProductImageResponseDto(
                        image.getId(),
                        image.getOrderIndex(),
                        image.getImageUrl()))
                .toList();
    }
}