package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.ecommerce.userservice.entity.User}
 */
@Value
@Builder
public class UserDto implements Serializable {
    
    int id;
    
    @Size(message = "Password must have at least 8 characters", min = 8)
    String password;
    
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    String email;
    Role role;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }
}