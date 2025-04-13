package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.CustomerDto;
import com.ecommerce.userservice.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/user/customer")
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @PostMapping("/create")
    public Mono<ResponseEntity<String>> createCustomer(@RequestBody CustomerDto customerDto) {

        return customerService.registerCustomer(customerDto)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error creating customer: " + e.getMessage())));
    }

    @DeleteMapping("/delete/{customerId}")
    public Mono<ResponseEntity<String>> deleteCustomer(@PathVariable("customerId") int customerId) {

        return customerService.deleteCustomerAccount(customerId)
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error deleting customer: " + e.getMessage())));
    }
}
