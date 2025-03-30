package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.ProductImageRecievedDto;
import com.ecommerce.product_service.entity.ProductImage;
import com.ecommerce.product_service.repository.ProductImageRepository;
import com.ecommerce.product_service.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductServiceImpl productService;

    @Override
    public void createProductImage(ProductImageRecievedDto productImage) {

        ProductImage newProductImage = ProductImageRecievedDto.toEntity(productImage);
        newProductImage.setProduct(productService.getProductById(productImage.getProductId()));
        productImageRepository.save(newProductImage);
    }

    @Override
    public void updateProductImage(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    @Override
    public void deleteProductImage(ProductImage productImage) {
        productImageRepository.delete(productImage);
    }

    @Override
    public ProductImage getProductImageById(int id) {
        return productImageRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductImage> getProductImagesByProductId(int productId) {
        return productImageRepository.findByProductId(productId);
    }
}
