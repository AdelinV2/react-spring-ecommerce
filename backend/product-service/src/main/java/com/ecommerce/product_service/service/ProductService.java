package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;

import java.util.List;

/**
 * Service for managing products.
 */
public interface ProductService {

    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    Product createProduct(Product product);

    /**
     * Update a product.
     *
     * @param product the product to update
     * @return the updated product
     */
    Product updateProduct(Product product);

    /**
     * Delete a product.
     *
     * @param product the product to delete
     */
    void deleteProduct(Product product);

    /**
     * Get a product by its ID.
     *
     * @param id the ID of the product
     * @return the product
     */
    Product getProductById(int id);

    /**
     * Get all products of a category.
     *
     * @return the list of products
     */
    List<Product> getProductsByCategory(String category);

    /**
     * Get all products by a name.
     *
     * @return the list of products
     */
    List<Product> getProductsByGeneralName(String name);
}
