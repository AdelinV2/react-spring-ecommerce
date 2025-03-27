package com.ecommerce.product_service.dto;

import lombok.*;

import java.io.Serializable;

@Value
@Builder
public class ProductMessageDto implements Serializable {

    int id;
    int sellerId;
    String name;
}
