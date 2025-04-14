package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.SellerDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.Seller;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.enums.Role;
import com.ecommerce.userservice.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl {

    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final KeycloakAdminClientService keycloakAdminClientService;

    @Transactional
    public Seller createSeller(SellerDto seller, User user) {

        Seller newSeller = SellerDto.toEntity(seller);
        newSeller.setUser(user);

        try {
            return sellerRepository.save(newSeller);
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
    public Mono<Void> deleteSeller(int sellerId) {

        return Mono.fromCallable(() -> sellerRepository.findById(sellerId)
                        .orElseThrow(() -> new RuntimeException("Seller not found")))
                .flatMap(seller -> keycloakAdminClientService.deleteUser(seller.getUser().getId())
                        .then(Mono.<Void>fromRunnable(() -> {
                            sellerRepository.delete(seller);
                            userService.deleteUser(seller.getUser());
                        })))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Seller getSellerById(int id) {
        return sellerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Mono<Seller> registerSeller(SellerDto sellerDto) {
        if (userService.findByEmail(sellerDto.getUser().getEmail()).isPresent()) {
            return Mono.error(new RuntimeException("User already exists"));
        }

        return Mono.fromCallable(() -> {
                    User newUser = UserDto.toEntity(sellerDto.getUser());
                    newUser.setRole(Role.SELLER);
                    return new Object[]{newUser, sellerDto.getUser().getPassword()};
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(tuple -> {
                    User newUser = (User) tuple[0];
                    String plainPassword = (String) tuple[1];
                    return keycloakAdminClientService.registerUserInKeycloak(
                                    newUser.getEmail(), newUser.getEmail(), plainPassword, Role.SELLER
                            )
                            .publishOn(Schedulers.boundedElastic())
                            .map(keycloakId -> {
                                newUser.setId(keycloakId);
                                User savedUser = userService.createUser(newUser);
                                return createSeller(sellerDto, savedUser);
                            });
                });
    }

}
