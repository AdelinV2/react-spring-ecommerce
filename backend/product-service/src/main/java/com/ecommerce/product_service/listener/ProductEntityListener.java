package com.ecommerce.product_service.listener;

import com.ecommerce.product_service.dto.ProductMessageDto;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.impl.ProductKafkaProducer;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityListener {

    private static ProductKafkaProducer staticKafkaProducer;

    @Autowired
    public void init(ProductKafkaProducer kafkaProducer) {
        ProductEntityListener.staticKafkaProducer = kafkaProducer;
    }

    @PreRemove
    public void preRemove(Product product) {

        ProductMessageDto productMessageDto = ProductMessageDto.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .name(product.getName())
                .build();

        staticKafkaProducer.sendProductDeletedMessage(productMessageDto);
    }
}
