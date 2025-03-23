package com.ecommerce.product_service.service;

public interface KafkaConsumerService {

    void consumeImageUploadEvent(String imageUrl);
}
