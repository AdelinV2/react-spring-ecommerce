package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.Product;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.Product}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    int sellerId;
    String name;
    String category;
    String subCategory;
    String description;
    double price;
    double oldPrice;
    int stock;
    double weight;
    boolean available;

    @Builder.Default
    List<ProductImageDto> images = new ArrayList<>();

    @Builder.Default
    List<SpecificationDto> specifications = new ArrayList<>();

    public static Product toEntity(ProductDto productDto) {

        return Product.builder()
                .sellerId(productDto.getSellerId())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .subCategory(productDto.getSubCategory())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .oldPrice(productDto.getOldPrice())
                .stock(productDto.getStock())
                .weight(productDto.getWeight())
                .available(productDto.isAvailable())
                .build();
    }
}