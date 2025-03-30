package com.ecommerce.imageservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDto implements Serializable {

    int productId;
    byte orderIndex;
    String imageUrl;
}
