package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ecommerce.product_service.entity.Product}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDto {

    int id;
    int sellerId;
    String name;
    String category;
    String description;
    double price;
    double oldPrice;
    int stock;
    double weight;
    boolean available;
    List<ProductImageDto> images;
    List<SpecificationDto> specifications;

    public static ProductDto fromEntity(com.ecommerce.product_service.entity.Product product) {

        return ProductDto.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .name(product.getName())
                .category(product.getCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .oldPrice(product.getOldPrice())
                .stock(product.getStock())
                .weight(product.getWeight())
                .available(product.isAvailable())
                .images(ProductImageDto.fromEntityList(product.getImages()))
                .specifications(SpecificationDto.fromEntityList(product.getSpecifications()))
                .build();
    }

    public static Product toEntity(ProductDto productDto) {

        return Product.builder()
                .id(productDto.getId())
                .sellerId(productDto.getSellerId())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .oldPrice(productDto.getOldPrice())
                .stock(productDto.getStock())
                .weight(productDto.getWeight())
                .available(productDto.isAvailable())
                .images(ProductImageDto.toEntityList(productDto.getImages()))
                .specifications(SpecificationDto.toEntityList(productDto.getSpecifications()))
                .build();
    }
}