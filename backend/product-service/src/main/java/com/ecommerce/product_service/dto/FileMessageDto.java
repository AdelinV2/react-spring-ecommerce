package com.ecommerce.product_service.dto;

import lombok.*;

import java.io.Serializable;

@Value
@Builder
public class FileMessageDto implements Serializable {

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