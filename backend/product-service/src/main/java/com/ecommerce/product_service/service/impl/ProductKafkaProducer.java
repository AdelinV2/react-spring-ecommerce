package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.FileMessageDto;
import com.ecommerce.product_service.dto.ProductMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendImageMessage(FileMessageDto message) {
        kafkaTemplate.send("product-image-topic", message.toString());
    }

    public void sendProductDeletedMessage(ProductMessageDto productMessageDto) {
        kafkaTemplate.send("product-deleted-topic", productMessageDto.toJsonString());
    }
}
