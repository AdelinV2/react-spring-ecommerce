package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.FileMessageDto;
import com.ecommerce.product_service.dto.ProductMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendImageMessage(FileMessageDto message) {
        try {
            String token = getAccessToken();
            message = message.withAuthToken(token);
            kafkaTemplate.send("product-image-topic", objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error sending image message to Kafka", e);
        }
    }

    public void sendProductDeletedMessage(ProductMessageDto productMessageDto) {

        try {
            String token = getAccessToken();
            productMessageDto = productMessageDto.withAuthToken(token);
            kafkaTemplate.send("product-deleted-topic", objectMapper.writeValueAsString(productMessageDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error sending product deleted message to Kafka", e);
        }
    }

    private String getAccessToken() {
        OAuth2AuthorizedClient client = authorizedClientManager.authorize(
                OAuth2AuthorizeRequest.withClientRegistrationId("product-service")
                        .principal("system")
                        .build());

        if (client == null) {
            throw new RuntimeException("Unable to authorize client for Kafka messaging");
        }

        return client.getAccessToken().getTokenValue();
    }
}
