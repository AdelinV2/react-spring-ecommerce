package com.ecommerce.product_service.dto;

import lombok.*;

@Value
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductMessageDto {

    int id;
    int sellerId;
    String name;

    public String toJsonString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"sellerId\":" + sellerId + "," +
                "\"name\":\"" + name + "\"" +
                "}";
    }
}
