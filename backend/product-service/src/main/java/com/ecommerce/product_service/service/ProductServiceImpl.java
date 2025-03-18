package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.entity.ProductImage;
import com.ecommerce.product_service.repository.ProductImageRepository;
import com.ecommerce.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;


    @Override
    public Product createProduct(Product product) {

        Product savedProduct = productRepository.save(product);

        for(ProductImage image : product.getImages()) {

            image.setProduct(savedProduct);
            productImageRepository.save(image);
        }

        return savedProduct;
    }

    @Override
    public Product updateProduct(Product product) {

        Product savedProduct = productRepository.save(product);

        for(ProductImage image : product.getImages()) {

            image.setProduct(savedProduct);
            productImageRepository.save(image);
        }

        return savedProduct;
    }

    @Override
    public void deleteProduct(Product product) {

        productRepository.delete(product);
    }

    @Override
    public Product getProductById(int id) {

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {

        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByGeneralName(String name) {

        return productRepository.findByNameContaining(name);
    }
}
