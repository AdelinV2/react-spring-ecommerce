package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.client.ReviewClient;
import com.ecommerce.product_service.dto.*;
import com.ecommerce.product_service.dto.request.ProductImageRequestDto;
import com.ecommerce.product_service.dto.request.ProductRequestDto;
import com.ecommerce.product_service.dto.request.SpecificationRequestDto;
import com.ecommerce.product_service.dto.response.ProductResponseDto;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.entity.Specification;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SpecificationServiceImpl specificationService;
    private final ProductKafkaProducer kafkaProducer;
    private final ReviewClient reviewClient;

    @Override
    public void saveProduct(ProductRequestDto product) throws IOException {

        Product savedProduct = productRepository.saveAndFlush(ProductRequestDto.toEntity(product));

        for (ProductImageRequestDto productImage : product.getImages()) {

            FileMessageDto message = FileMessageDto.builder()
                    .productId(savedProduct.getId())
                    .orderIndex(productImage.getOrderIndex())
                    .fileName(productImage.getFileName())
                    .fileType(productImage.getFileType())
                    .data(productImage.getData())
                    .build();

            kafkaProducer.sendImageMessage(message);
        }

        for (SpecificationRequestDto specification : product.getSpecifications()) {

            Specification newSpecification = SpecificationRequestDto.toEntity(specification);
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

        return getProductCardDtos(products);
    }

    @Override
    public List<ProductCardDto> getProductCardBySubCategories(List<String> subCategories) {

     List<Product> products = productRepository.findBySubCategoryIn(subCategories);

        return getProductCardDtos(products);
    }

    private List<ProductCardDto> getProductCardDtos(List<Product> products) {
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

    @Override
    public ProductResponseDto getProductResponseById(int id) {

        Product product = getProductById(id);

        if (product == null) {
            return null;
        }

        ProductResponseDto productResponseDto = ProductResponseDto.fromEntity(product);

        AverageReviewDto avg = reviewClient.getProductAverage(product.getId());
        productResponseDto.setReviewScore(avg.getAverage());
        productResponseDto.setReviewCount(avg.getCount());

        return productResponseDto;
    }
}
