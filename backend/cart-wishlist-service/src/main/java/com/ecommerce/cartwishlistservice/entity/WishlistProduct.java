package com.ecommerce.cartwishlistservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "wishlist_product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "available")
    private boolean isAvailable;

    @Column(name = "discounted")
    private boolean isDiscounted;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Date createdAt;
}
