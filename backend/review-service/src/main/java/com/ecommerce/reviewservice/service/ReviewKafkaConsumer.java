package com.ecommerce.reviewservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(topics = {"review-topic", "product-deleted-topic"}, groupId = "review-group")
@RequiredArgsConstructor
public class ReviewKafkaConsumer {

    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "product-deleted-topic", groupId = "review-group")
    public void consumeProductDeletedMessage(String message) {

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            int productId = jsonNode.get("productId").asInt();
            reviewService.deleteReviewsByProductId(productId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
