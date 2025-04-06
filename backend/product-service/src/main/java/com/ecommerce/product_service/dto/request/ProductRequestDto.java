package com.ecommerce.product_service.dto.request;

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
public class ProductRequestDto implements Serializable {

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
    List<ProductImageRequestDto> images = new ArrayList<>();

    @Builder.Default
    List<SpecificationRequestDto> specifications = new ArrayList<>();

    public static Product toEntity(ProductRequestDto productRequestDto) {

        return Product.builder()
                .sellerId(productRequestDto.getSellerId())
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .subCategory(productRequestDto.getSubCategory())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .oldPrice(productRequestDto.getOldPrice())
                .stock(productRequestDto.getStock())
                .weight(productRequestDto.getWeight())
                .available(productRequestDto.isAvailable())
                .build();
    }
}