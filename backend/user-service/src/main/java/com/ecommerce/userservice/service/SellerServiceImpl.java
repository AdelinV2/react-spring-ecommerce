package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.SellerDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.Seller;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.enums.Role;
import com.ecommerce.userservice.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl {

    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final KeycloakAdminClientService keycloakAdminClientService;

    @Transactional
    public void createSeller(SellerDto seller, User user) {

        Seller newSeller = SellerDto.toEntity(seller);
        newSeller.setUser(user);

        try {
            sellerRepository.save(newSeller);
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

    @Transactional
    public Mono<Void> registerSeller(SellerDto sellerDto) {
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
                    ).map(keycloakId -> {
                        newUser.setId(keycloakId);
                        User savedUser = userService.createUser(newUser);
                        createSeller(sellerDto, savedUser);
                        return savedUser;
                    });
                })
                .then();
    }
}
