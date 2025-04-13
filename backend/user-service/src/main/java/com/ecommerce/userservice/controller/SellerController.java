package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.SellerDto;
import com.ecommerce.userservice.service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/delete/{sellerId}")
    public Mono<ResponseEntity<String>> deleteSeller(@PathVariable("sellerId") int sellerId) {

        return sellerService.deleteSeller(sellerId)
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body("Seller deleted successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error deleting seller: " + e.getMessage())));
    }
}
