package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.entity.ProductImage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductKafkaConsumer {

    private final ProductImageServiceImpl productImageService;

    @KafkaListener(topics = "product-image-created-topic", groupId = "product-group")
    public void consumeProductImage(ConsumerRecord<String, ProductImage> record) {

        ProductImage productImage = record.value();
        productImageService.createProductImage(productImage);
    }
}
