package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.client.ReviewClient;
import com.ecommerce.product_service.dto.*;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.entity.Specification;
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
    private final ReviewClient reviewClient;

    @Override
    public void saveProduct(ProductDto product) throws IOException {

        Product savedProduct = productRepository.saveAndFlush(ProductDto.toEntity(product));

        for (ProductImageDto productImage : product.getImages()) {

            FileMessageDto message = FileMessageDto.builder()
                    .productId(savedProduct.getId())
                    .orderIndex(productImage.getOrderIndex())
                    .fileName(productImage.getFileName())
                    .fileType(productImage.getFileType())
                    .data(productImage.getData())
                    .build();

            kafkaProducer.sendImageMessage(message);
        }

        for (SpecificationDto specification : product.getSpecifications()) {

            Specification newSpecification = SpecificationDto.toEntity(specification);
            newSpecification.setProduct(savedProduct);
            specificationService.saveSpecification(newSpecification);
        }
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

    @Override
    public List<ProductCardDto> getProductCardsByCategories(List<String> categories) {

        List<Product> products = productRepository.findByCategoryIn(categories);

        return products.stream().map(product -> {

            AverageReviewDto avg = reviewClient.getProductAverage(product.getId());

            return  ProductCardDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .category(product.getCategory())
                    .subCategory(product.getSubCategory())
                    .price(product.getPrice())
                    .oldPrice(product.getOldPrice())
                    .stock(product.getStock())
                    .reviewScore(avg.getAverage())
                    .reviewCount(avg.getCount())
                    .imageUrl(product.getImages().isEmpty() ? null : product.getImages().get(0).getImageUrl())
                    .build();
        }).toList();
    }
}
