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

    public static ProductImageFileDto fromString(String message) {

        String[] parts = message.split(",");
        int productId = Integer.parseInt(parts[0].split(": ")[1]);
        byte orderIndex = Byte.parseByte(parts[1].split(": ")[1]);
        String fileName = parts[2].split(": ")[1];
        String fileType = parts[3].split(": ")[1];
        String data = parts[4].split(": ")[1];

        return ProductImageFileDto.builder()
                .productId(productId)
                .orderIndex(orderIndex)
                .fileName(fileName)
                .fileType(fileType)
                .data(data)
                .build();
    }

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