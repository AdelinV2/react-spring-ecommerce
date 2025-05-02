package com.ecommerce.cartwishlistservice.service;

import com.ecommerce.cartwishlistservice.dto.CartProductDto;
import com.ecommerce.cartwishlistservice.entity.CartProduct;
import com.ecommerce.cartwishlistservice.repository.CartProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartProductService {

    private final CartProductRepository cartProductRepository;

    public CartProduct saveCartProduct(CartProductDto cartProductDto) {

        CartProduct cartProduct = CartProductDto.toEntity(cartProductDto);
        return cartProductRepository.save(cartProduct);
    }

    public List<CartProduct> getAllCartProductsByUserId(String userId) {

        return cartProductRepository.findByUserId(userId);
    }

    public void deleteCartProductsByProductId(int productId) {

        cartProductRepository.deleteAllByProductId(productId);
    }

    public void deleteCartProductById(int cartProductId) {
        cartProductRepository.deleteById(cartProductId);
    }

    public void deleteAllCartProductsByUserId(String userId) {

        cartProductRepository.deleteAllByUserId(userId);
    }

    public CartProduct findById(int id) {
        return cartProductRepository.findById(id).orElse(null);
    }
}
