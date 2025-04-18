package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.CustomerDto;
import com.ecommerce.userservice.entity.Customer;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
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
    public Mono<Void> deleteCustomerAccount(int customerId) {
        return Mono.fromCallable(() -> customerRepository.findById(customerId)
                        .orElseThrow(() -> new RuntimeException("Customer not found")))
                .flatMap(customer -> keycloakAdminClientService.deleteUser(customer.getUser().getId())
                        .then(Mono.<Void>fromRunnable(() -> {
                            customerRepository.delete(customer);
                            userService.deleteUser(customer.getUser());
                        })))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Mono<Customer> registerCustomer(CustomerDto customerDto) {
        if (userService.findByEmail(customerDto.getUser().getEmail()).isPresent()) {
            return Mono.error(new RuntimeException("User already exists"));
        }
        return Mono.fromCallable(() -> {
                    User newUser = UserDto.toEntity(customerDto.getUser());
                    newUser.setRole(Role.CUSTOMER);
                    return new Object[]{newUser, customerDto.getUser().getPassword()};
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(tuple -> {
                    User newUser = (User) tuple[0];
                    String plainPassword = (String) tuple[1];
                    return keycloakAdminClientService.registerUserInKeycloak(
                                    newUser.getEmail(), newUser.getEmail(), plainPassword, Role.CUSTOMER)
                            .publishOn(Schedulers.boundedElastic())
                            .map(keycloakId -> {
                                newUser.setId(keycloakId);
                                User savedUser = userService.createUser(newUser);
                                return createCustomer(customerDto, savedUser);
                            });
                });
    }
}
