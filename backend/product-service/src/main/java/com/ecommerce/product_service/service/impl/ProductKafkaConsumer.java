package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.ProductImageRecievedDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductKafkaConsumer {

    private final ProductImageServiceImpl productImageService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "product-image-created-topic", groupId = "product-service")
    public void consumeProductImage(String message) {

        try {
            ProductImageRecievedDto productImage = objectMapper.readValue(message, ProductImageRecievedDto.class);
            productImageService.createProductImage(productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
