package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {


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
    public ProductImage getProductImageById(String id) {
        return null;
    }

    @Override
    public List<ProductImage> getProductImagesByProductId(String productId) {
        return List.of();
    }
}
