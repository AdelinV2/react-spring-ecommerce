package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ProductCardDto;
import com.ecommerce.product_service.dto.request.ProductRequestDto;
import com.ecommerce.product_service.dto.response.ProductResponseDto;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") int id) {

        ProductResponseDto product = productService.getProductResponseById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping("/card-categories")
    public ResponseEntity<List<ProductCardDto>> getProductCardsByCategories(@RequestBody List<String> categories) {

        List<ProductCardDto> productCards = productService.getProductCardsByCategories(categories);

        if (productCards.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productCards);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto product) throws IOException {

        Product savedProduct = productService.saveProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequestDto product) throws IOException {

        productService.saveProduct(product);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {

        Product product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(product);

        return ResponseEntity.ok().build();
    }
}
