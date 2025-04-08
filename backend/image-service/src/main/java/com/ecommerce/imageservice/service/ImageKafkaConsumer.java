package com.ecommerce.imageservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageKafkaConsumer {

    private final ImageService imageService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtDecoder jwtDecoder;

    private void validateToken(String message) throws Exception {

        JsonNode node = objectMapper.readTree(message);

        if (!node.has("authToken") || node.get("authToken").asText().isEmpty()) {
            throw new RuntimeException("Unauthorized message: token missing");
        }

        jwtDecoder.decode(node.get("authToken").asText());
    }

    @KafkaListener(topics = "product-image-topic", groupId = "image-service")
    public void consumeProductImage(String message) {
        try {
            validateToken(message);
            imageService.uploadProductImage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}