package com.ecommerce.imageservice.service;

import com.ecommerce.imageservice.dto.ProductImageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendProductImageMessage(ProductImageDto productImageDto) {

        try {
            String token = getAccessToken();
            productImageDto = productImageDto.withAuthToken(token);
            kafkaTemplate.send("product-image-created-topic", objectMapper.writeValueAsString(productImageDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAccessToken() {

        OAuth2AuthorizedClient client = authorizedClientManager.authorize(
                OAuth2AuthorizeRequest.withClientRegistrationId("image-service")
                        .principal("system")
                        .build());

        if (client == null) {
            throw new RuntimeException("Unable to authorize client for Kafka messaging");
        }

        return client.getAccessToken().getTokenValue();
    }
}
