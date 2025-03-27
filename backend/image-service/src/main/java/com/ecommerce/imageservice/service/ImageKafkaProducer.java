package com.ecommerce.imageservice.service;

import com.ecommerce.imageservice.dto.ProductImageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendProductImageMessage(ProductImageDto productImageDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            kafkaTemplate.send("product-image-topic", objectMapper.writeValueAsString(productImageDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
