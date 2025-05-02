package com.ecommerce.cartwishlistservice.service;

import com.ecommerce.cartwishlistservice.repository.WishlistProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistProductService {

    private final WishlistProductRepository wishlistProductRepository;

    public void deleteWishlistsByProductId(int productId) {
        wishlistProductRepository.deleteAllByProductId(productId);
    }
}
