package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.SellerDto;
import com.ecommerce.userservice.entity.Seller;
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
    public Mono<ResponseEntity<Seller>> createSeller(@RequestBody SellerDto sellerDto) {
        return sellerService.registerSeller(sellerDto)
                .map(seller -> ResponseEntity.status(HttpStatus.CREATED).body(seller))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @DeleteMapping("/delete/{sellerId}")
    public Mono<ResponseEntity<String>> deleteSeller(@PathVariable("sellerId") int sellerId) {

        return sellerService.deleteSeller(sellerId)
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body("Seller deleted successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error deleting seller: " + e.getMessage())));
    }
}
