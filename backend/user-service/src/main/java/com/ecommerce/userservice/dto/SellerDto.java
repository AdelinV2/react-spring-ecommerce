package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Seller}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto implements Serializable {

    UserDto user;
    String companyName;
    String address;
    String city;
    String state;

    public static SellerDto fromEntity(Seller seller) {
        return SellerDto.builder()
                .user(UserDto.fromEntity(seller.getUser()))
                .companyName(seller.getCompanyName())
                .address(seller.getAddress())
                .city(seller.getCity())
                .state(seller.getState())
                .build();
    }

    public static Seller toEntity(SellerDto sellerDto) {
        return Seller.builder()
                .user(UserDto.toEntity(sellerDto.getUser()))
                .companyName(sellerDto.getCompanyName())
                .address(sellerDto.getAddress())
                .city(sellerDto.getCity())
                .state(sellerDto.getState())
                .build();
    }
}