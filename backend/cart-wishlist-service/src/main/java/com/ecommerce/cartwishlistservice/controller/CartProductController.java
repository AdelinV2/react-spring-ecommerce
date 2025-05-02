package com.ecommerce.cartwishlistservice.controller;

import com.ecommerce.cartwishlistservice.dto.CartProductDto;
import com.ecommerce.cartwishlistservice.entity.CartProduct;
import com.ecommerce.cartwishlistservice.service.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartProductController {

    private final CartProductService cartProductService;

    @PostMapping
    public ResponseEntity<CartProduct> addProductToCart (@RequestBody CartProductDto cartProductDto) {

        CartProduct cartProduct = cartProductService.saveCartProduct(cartProductDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartProduct);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartProduct>> getAllCartProductsByUserId(@PathVariable("userId") String userId) {

        List<CartProduct> cartProducts = cartProductService.getAllCartProductsByUserId(userId);

        return ResponseEntity.ok(cartProducts);
    }

    @DeleteMapping("/{cartProductId}")
    public ResponseEntity<Void> deleteCartProductsByProductId(@PathVariable("cartProductId") int id,
                                                              Principal principal) {

        CartProduct cartProduct = cartProductService.findById(id);

        if (!cartProduct.getUserId().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        cartProductService.deleteCartProductById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
