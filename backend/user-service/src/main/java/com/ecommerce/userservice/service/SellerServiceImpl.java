package com.ecommerce.userservice.service;

import com.ecommerce.userservice.entity.Seller;
import com.ecommerce.userservice.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl {

    private final SellerRepository sellerRepository;
    private final UserService userService;

    @Transactional
    public void createSeller(Seller seller) {
        try {
            sellerRepository.save(seller);
        } catch (Exception e) {
            throw new RuntimeException("The email or phone number is already in use", e);
        }
    }

    @Transactional
    public void updateSeller(Seller seller) {
        try {
            sellerRepository.save(seller);
        } catch (Exception e) {
            throw new RuntimeException("The email or phone number is already in use", e);
        }
    }

    @Transactional
    public void deleteSeller(Seller seller) {
        sellerRepository.delete(seller);
    }

    public Seller getSellerById(int id) {
        return sellerRepository.findById(id).orElse(null);
    }
}
