package com.ecommerce.cartwishlistservice.service;

import com.ecommerce.cartwishlistservice.dto.WishlistProductDto;
import com.ecommerce.cartwishlistservice.entity.WishlistProduct;
import com.ecommerce.cartwishlistservice.repository.WishlistProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistProductService {

    private final WishlistProductRepository wishlistProductRepository;

    public WishlistProduct saveWishlistProduct(WishlistProductDto wishlistProductDto) {

        return wishlistProductRepository.save(wishlistProductDto.toEntity());
    }

    public List<WishlistProduct> getAllWishlistProductsByUserId(String userId) {

        return wishlistProductRepository.findByUserId(userId);
    }

    public void deleteWishlistsByProductId(int productId) {

        wishlistProductRepository.deleteAllByProductId(productId);
    }

    public WishlistProduct findById(int id) {
        return wishlistProductRepository.findById(id).orElse(null);
    }
}
