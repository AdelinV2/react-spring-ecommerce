package com.ecommerce.imageservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductImageFileDto implements Serializable {

    int productId;
    byte orderIndex;
    String fileName;
    String fileType;
    String data;
    @With
    String authToken;
}