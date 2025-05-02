package com.ecommerce.cartwishlistservice.controller;

import com.ecommerce.cartwishlistservice.dto.WishlistProductDto;
import com.ecommerce.cartwishlistservice.entity.WishlistProduct;
import com.ecommerce.cartwishlistservice.service.WishlistProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistProductController {

    private final WishlistProductService wishlistProductService;

    @PostMapping
    public ResponseEntity<WishlistProduct> addProductToWishlist(@RequestBody WishlistProductDto wishlistProductDto) {

        WishlistProduct wishlistProduct = wishlistProductService.saveWishlistProduct(wishlistProductDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistProduct);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistProduct>> getAllWishlistProductsByUserId(@PathVariable("userId") String userId) {

        List<WishlistProduct> wishlistProducts = wishlistProductService.getAllWishlistProductsByUserId(userId);

        return ResponseEntity.ok(wishlistProducts);
    }

    @DeleteMapping("/{wishlistProductId}")
    public ResponseEntity<Void> deleteWishlistProductsById(@PathVariable("wishlistProductId") int id,
                                                           Principal principal) {

        WishlistProduct wishlistProduct = wishlistProductService.findById(id);

        if (!wishlistProduct.getUserId().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        wishlistProductService.deleteWishlistsByProductId(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
