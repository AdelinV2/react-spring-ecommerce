package com.ecommerce.reviewservice.dto;

import com.ecommerce.reviewservice.entity.Review;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.ecommerce.reviewservice.entity.Review}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto implements Serializable {

    @NotEmpty
    int productId;

    @NotEmpty
    int userId;

    @Size(message = "Rating must be between 1 and 5", min = 1, max = 5)
    byte rating;

    @Size(message = "Message must be less than 500 characters", max = 500)
    String message;

    public static Review toEntity(ReviewDto reviewDto) {
        return Review.builder()
                .productId(reviewDto.getProductId())
                .userId(reviewDto.getUserId())
                .rating(reviewDto.getRating())
                .message(reviewDto.getMessage())
                .build();
    }
}