package com.ecommerce.userservice.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.ecommerce.userservice.entity.Customer;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.ecommerce.userservice.entity.Customer}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {

    UserDto user;
    
    @Size(message = "First name must be between 2 and 30 characters", min = 2, max = 30)
    String firstName;
    
    @Size(message = "Last name must be between 2 and 30 characters", min = 2, max = 30)
    String lastName;
    
    String phone;
    String address1;
    String address2;
    String city;
    String state;

    public static CustomerDto fromEntity(Customer customer) {
        return CustomerDto.builder()
                .user(UserDto.fromEntity(customer.getUser()))
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .address1(customer.getAddress1())
                .address2(customer.getAddress2())
                .city(customer.getCity())
                .state(customer.getState())
                .build();
    }

    public static Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .user(UserDto.toEntity(customerDto.getUser()))
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .phone(customerDto.getPhone())
                .address1(customerDto.getAddress1())
                .address2(customerDto.getAddress2())
                .city(customerDto.getCity())
                .state(customerDto.getState())
                .build();
    }
}