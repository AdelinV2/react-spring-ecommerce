package com.ecommerce.product_service.dto;

import lombok.*;

@Value
@Getter
@Setter
@Builder
@AllArgsConstructor
public class FileMessageDto {

    int productId;
    byte orderIndex;
    String fileName;
    String fileType;
    String data;

    @Override
    public String toString() {
        return "{" +
                "\"productId\": " + productId + "," +
                "\"orderIndex\": " + orderIndex + "," +
                "\"fileName\": \"" + fileName + "\"," +
                "\"fileType\": \"" + fileType + "\"," +
                "\"data\": \"" + data + "\"" +
                "}";
    }
}