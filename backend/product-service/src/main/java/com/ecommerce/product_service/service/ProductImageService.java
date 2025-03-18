package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.ProductImage;

import java.util.List;

public interface ProductImageService {

    /**
     * Create a new product image.
     *
     * @param productImage the product image to create
     */
    void createProductImage(ProductImage productImage);

    /**
     * Update a product image.
     *
     * @param productImage the product image to update
     */
    void updateProductImage(ProductImage productImage);

    /**
     * Delete a product image.
     *
     * @param productImage the product image to delete
     */
    void deleteProductImage(ProductImage productImage);

    /**
     * Get a product image by its ID.
     *
     * @param id the ID of the product image
     * @return the product image
     */
    ProductImage getProductImageById(int id);

    /**
     * Get all product images of a product.
     *
     * @param productId the ID of the product
     * @return the list of product images
     */
    List<ProductImage> getProductImagesByProductId(int productId);


}
