package com.ecommerce.imageservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductImageDto implements Serializable {

    int productId;
    byte orderIndex;
    String imageUrl;
    @With
    String authToken;
}
