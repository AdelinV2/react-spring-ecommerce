package com.ecommerce.product_service.dto.response;

import com.ecommerce.product_service.entity.Product;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.Product}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto implements Serializable {

    int id;
    int sellerId;
    String name;
    String category;
    String subCategory;
    String description;
    double price;
    double oldPrice;
    int stock;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    double weight;
    boolean available;
    double reviewScore;
    int reviewCount;
    List<ProductImageResponseDto> images;
    List<SpecificationResponseDto> specifications;

    public static ProductResponseDto fromEntity(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .name(product.getName())
                .category(product.getCategory())
                .subCategory(product.getSubCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .oldPrice(product.getOldPrice())
                .stock(product.getStock())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .weight(product.getWeight())
                .available(product.isAvailable())
                .images(ProductImageResponseDto.fromEntities(product.getImages()))
                .specifications(SpecificationResponseDto.fromEntities(product.getSpecifications()))
                .build();
    }
}