package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.ProductImageRecievedDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductKafkaConsumer {

    private final ProductImageServiceImpl productImageService;
    private final ObjectMapper objectMapper;
    private final JwtDecoder jwtDecoder;

    private void validateToken(String message) throws Exception {
        JsonNode node = objectMapper.readTree(message);

        if (!node.has("authToken") || node.get("authToken").asText().isEmpty()) {
            throw new RuntimeException("Unauthorized message");
        }

        jwtDecoder.decode(node.get("authToken").asText());
    }

    @KafkaListener(topics = "product-image-created-topic", groupId = "product-service")
    public void consumeProductImage(String message) {

        try {
            validateToken(message);
            ProductImageRecievedDto productImage = objectMapper.readValue(message, ProductImageRecievedDto.class);
            productImageService.createProductImage(productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
