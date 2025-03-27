package com.ecommerce.imageservice.dto;

import lombok.*;

import java.io.Serializable;

@Value
@Builder
public class ProductImageFileDto implements Serializable {

    int productId;
    byte orderIndex;
    String fileName;
    String fileType;
    String data;
}