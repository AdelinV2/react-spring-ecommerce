package com.ecommerce.product_service.service;

public interface KafkaProducerService {

    void sendImageUploadEvent(String imageUrl);
}
