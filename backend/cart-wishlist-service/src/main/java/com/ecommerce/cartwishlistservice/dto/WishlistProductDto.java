package com.ecommerce.cartwishlistservice.dto;

import com.ecommerce.cartwishlistservice.entity.WishlistProduct;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.ecommerce.cartwishlistservice.entity.WishlistProduct}
 */
@Value
@Builder
@Data
public class WishlistProductDto implements Serializable {

    int productId;
    String userId;
    boolean isAvailable;
    boolean isDiscounted;

    public WishlistProduct toEntity() {
        return WishlistProduct.builder()
                .productId(productId)
                .userId(userId)
                .isAvailable(isAvailable)
                .isDiscounted(isDiscounted)
                .build();
    }
}