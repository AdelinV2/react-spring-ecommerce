package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.entity.ProductImage;
import com.ecommerce.product_service.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageService productImageService;

    @Override
    public void createProductImage(ProductImage productImage) {

    }

    @Override
    public void updateProductImage(ProductImage productImage) {

    }

    @Override
    public void deleteProductImage(ProductImage productImage) {

    }

    @Override
    public ProductImage getProductImageById(int id) {
        return null;
    }

    @Override
    public List<ProductImage> getProductImagesByProductId(int productId) {
        return List.of();
    }
}
