package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.dto.AverageDto;
import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews (@PathVariable("productId") int productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);

        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/product-average/{productId}")
    public ResponseEntity<AverageDto> getProductAverage (@PathVariable("productId") int productId) {

        AverageDto avg = reviewService.getAverageByProductId(productId);

        return ResponseEntity.ok(avg);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto review) {
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.status(201).body(createdReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") int id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }

}
