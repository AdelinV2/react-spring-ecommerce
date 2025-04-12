package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.CustomerDto;
import com.ecommerce.userservice.entity.Customer;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.enums.Role;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {

    private final UserService userService;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final KeycloakAdminClientService keycloakAdminClientService;

    @Transactional
    public Customer createCustomer(CustomerDto customer, User user) {

        Customer newCustomer = CustomerDto.toEntity(customer);
        newCustomer.setUser(user);

        try {
            return customerRepository.save(newCustomer);
        } catch (Exception e) {
            throw new RuntimeException("The email or phone number is already in use", e);
        }
    }

    @Transactional
    public Customer updateCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("The email or phone number is already in use", e);
        }
    }

    @Transactional
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Mono<Void> registerCustomer(CustomerDto customerDto) {
        return Mono.fromCallable(() -> {
            User newUser = UserDto.toEntity(customerDto.getUser());
            newUser.setRole(Role.CUSTOMER);
            User savedUser = userService.createUser(newUser);
            createCustomer(customerDto, savedUser);
            return new Object[]{savedUser, customerDto.getUser().getPassword()};
        })
        .subscribeOn(Schedulers.boundedElastic())
        .flatMap(tuple -> {
            User savedUser = (User) tuple[0];
            String plainPassword = (String) tuple[1];
            return keycloakAdminClientService.registerUserInKeycloak(
                    savedUser.getEmail(), savedUser.getEmail(), plainPassword, Role.CUSTOMER
            ).flatMap(keycloakId -> {
                savedUser.setId(keycloakId);
                userService.updateUser(savedUser);
                return Mono.empty();
            });
        });
    }
}
