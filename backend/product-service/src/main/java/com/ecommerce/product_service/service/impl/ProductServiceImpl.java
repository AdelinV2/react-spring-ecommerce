package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.FileMessageDto;
import com.ecommerce.product_service.dto.ProductDto;
import com.ecommerce.product_service.dto.ProductImageDto;
import com.ecommerce.product_service.dto.SpecificationDto;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SpecificationServiceImpl specificationService;
    private final ProductKafkaProducer kafkaProducer;

    @Override
    public void createProduct(ProductDto product) throws IOException {

        for (ProductImageDto productImage : product.getImages()) {

            FileMessageDto message = FileMessageDto.builder()
                    .productId(productImage.getProductId())
                    .orderIndex(productImage.getOrderIndex())
                    .fileName(productImage.getFile().getOriginalFilename())
                    .fileType(productImage.getFile().getContentType())
                    .data(Base64.getEncoder().encodeToString(productImage.getFile().getBytes()))
                    .build();

            kafkaProducer.sendImageMessage(message);
        }

        for (SpecificationDto specification : product.getSpecifications()) {
            specificationService.saveSpecification(SpecificationDto.toEntity(specification));
        }

        Product newProduct = ProductDto.toEntity(product);

        productRepository.save(newProduct);
    }

    @Override
    public void updateProduct(ProductDto product) {

    }

    @Override
    public void deleteProduct(Product product) {

        if (product == null) {
            return;
        }

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
