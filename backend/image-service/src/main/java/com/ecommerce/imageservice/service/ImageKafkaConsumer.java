package com.ecommerce.imageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageKafkaConsumer {

    private final ImageService imageService;

    @KafkaListener(topics = "product-image-topic", groupId = "image-service")
    public void consumeProductImage(String message) {

        imageService.uploadProductImage(message);
    }

    @KafkaListener(topics = "product-deleted-topic", groupId = "image-service")
    public void consumeProductDeleted(String message) {
        imageService.deleteProductImages(message);
    }
}