
package com.ecommerce.imageservice.service;

import com.ecommerce.imageservice.dto.ProductImageDto;
import com.ecommerce.imageservice.dto.ProductImageFileDto;
import com.ecommerce.imageservice.util.ContainerName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AzureBlobStorageService azureBlobStorageService;
    private final ImageKafkaProducer imageKafkaProducer;

    public void uploadProductImage(String message) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            ProductImageFileDto productImageFileDto = mapper.readValue(message, ProductImageFileDto.class);

            String newFileName = productImageFileDto.getProductId() + "_" + productImageFileDto.getFileName();

            MultipartFile multipartFile = new MockMultipartFile(
                    newFileName,
                    newFileName,
                    productImageFileDto.getFileType(),
                    Base64.getDecoder().decode(productImageFileDto.getData())
            );

            String url = azureBlobStorageService.uploadImage(ContainerName.PRODUCT, multipartFile);

            ProductImageDto productImageDto = ProductImageDto.builder()
                    .imageUrl(url)
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

            azureBlobStorageService.deleteImages(ContainerName.PRODUCT, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}