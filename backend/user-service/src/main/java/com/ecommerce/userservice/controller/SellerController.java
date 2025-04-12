package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.SellerDto;
import com.ecommerce.userservice.service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerServiceImpl sellerService;

    @PostMapping("/create")
    public Mono<ResponseEntity<String>> createSeller(@RequestBody SellerDto sellerDto) {

        return sellerService.registerSeller(sellerDto)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Seller created successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error creating seller: " + e.getMessage())));
    }
}
