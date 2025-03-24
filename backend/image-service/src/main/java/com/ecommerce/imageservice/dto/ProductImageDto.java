package com.ecommerce.imageservice.dto;

import lombok.*;

@Value
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductImageDto {

    int productId;
    byte orderIndex;
    String imageUrl;

    public String toJsonString() {
        return "{" +
                "\"productId\":" + productId + "," +
                "\"orderIndex\":" + orderIndex + "," +
                "\"imageUrl\":\"" + imageUrl + "\"" +
                "}";
    }
}
