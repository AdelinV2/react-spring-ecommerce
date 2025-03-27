package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.ProductImage;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.ProductImage}
 */
@Value
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProductImageDto implements Serializable {

    int id;
    int productId;
    byte orderIndex;
    String fileName;
    String fileType;
    String data;

    public static ProductImageDto fromEntity(com.ecommerce.product_service.entity.ProductImage productImage) {

        return ProductImageDto.builder()
                .id(productImage.getId())
                .productId(productImage.getProduct().getId())
                .orderIndex(productImage.getOrderIndex())
                .build();
    }

    public static ProductImage toEntity(ProductImageDto productImageDto) {

        return ProductImage.builder()
                .id(productImageDto.getId())
                .orderIndex(productImageDto.getOrderIndex())
                .build();
    }

    public static List<ProductImageDto> fromEntityList(List<ProductImage> images) {

        return images.stream()
                .map(ProductImageDto::fromEntity)
                .toList();
    }

    public static List<ProductImage> toEntityList(List<ProductImageDto> imageDtos) {

        return imageDtos.stream()
                .map(ProductImageDto::toEntity)
                .toList();
    }
}