package com.ecommerce.cartwishlistservice.dto;

import com.ecommerce.cartwishlistservice.entity.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartProductDto {

    private int productId;
    private String userId;
    private int quantity;

    public static CartProduct toEntity(CartProductDto cartProductDto) {
        return CartProduct.builder()
                .productId(cartProductDto.getProductId())
                .userId(cartProductDto.getUserId())
                .quantity(cartProductDto.getQuantity())
                .build();
    }
}
