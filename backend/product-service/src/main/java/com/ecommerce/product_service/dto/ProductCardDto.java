package com.ecommerce.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCardDto implements Serializable {

    private int id;
    private String name;
    private String category;
    private String subCategory;
    private double price;
    private double oldPrice;
    private int stock;
    private double reviewScore;
    private int reviewCount;
    private String imageUrl;
}
