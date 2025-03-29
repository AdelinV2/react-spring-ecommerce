package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ProductDto;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.impl.ProductImageServiceImpl;
import com.ecommerce.product_service.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {

        Product product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping("api/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto product) throws IOException {

        productService.saveProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto product) throws IOException {

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
