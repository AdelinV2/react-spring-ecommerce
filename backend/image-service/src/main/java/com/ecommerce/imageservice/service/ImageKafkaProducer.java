package com.ecommerce.imageservice.service;

import com.ecommerce.imageservice.dto.ProductImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendProductImageMessage(ProductImageDto productImageDto) {
        kafkaTemplate.send("product-image-topic", productImageDto.toJsonString());
    }
}
