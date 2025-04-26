package com.ecommerce.imageservice.service;

import com.ecommerce.imageservice.dto.ProductImageDto;
import com.ecommerce.imageservice.dto.ProductImageFileDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.imagekit.sdk.models.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageKitService imageKitService;
    private final ImageKafkaProducer imageKafkaProducer;

    public void uploadProductImage(String message) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            ProductImageFileDto productImageFileDto = mapper.readValue(message, ProductImageFileDto.class);

            String fileName = productImageFileDto.getProductId() + "_" + productImageFileDto.getFileName();

            Result result = imageKitService.uploadProductImage(productImageFileDto.getData(), fileName);

            ProductImageDto productImageDto = ProductImageDto.builder()
                    .imageUrl(result.getUrl())
                    .orderIndex(productImageFileDto.getOrderIndex())
                    .productId(productImageFileDto.getProductId())
                    .build();

            imageKafkaProducer.sendProductImageMessage(productImageDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProductImages(String message) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> data = mapper.readValue(message, Map.class);
            String productId = data.get("id").toString();

            imageKitService.deleteProductImagesByProductId(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}