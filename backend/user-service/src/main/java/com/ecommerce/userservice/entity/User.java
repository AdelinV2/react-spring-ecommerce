package com.ecommerce.userservice.entity;

import com.ecommerce.userservice.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Transient
    @Min(value = 8, message = "Password must have at least 8 characters")
    @Max(value = 20, message = "Password must have at most 20 characters")
    @Pattern.List({
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number"),
            @Pattern(regexp = ".*[!@#$%^&*()].*", message = "Password must contain at least one symbol")
    })
    private String password;

    @Column(name = "email", unique = true)
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Transient
    private Role role;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @PrePersist
    private void prePersist() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = java.util.UUID.randomUUID().toString();
        }
    }
}
