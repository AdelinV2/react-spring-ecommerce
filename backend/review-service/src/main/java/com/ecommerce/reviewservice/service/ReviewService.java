package com.ecommerce.reviewservice.service;

import com.ecommerce.reviewservice.dto.AverageDto;
import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByProductId(int productId) {
        return reviewRepository.findByProductId(productId);
    }

    public AverageDto getAverageByProductId(int productId) {

        List<Review> reviews = reviewRepository.findByProductId(productId);

        if (reviews.isEmpty()) {
            return AverageDto.builder()
                    .average(0)
                    .count(0)
                    .build();
        }

        int count = reviews.size();
        double avg = reviews.stream()
                .mapToDouble(Review::getRating)
                .sum() / count;

        return AverageDto.builder()
                .average(avg)
                .count(count)
                .build();
    }

    public Review createReview(ReviewDto review) {

        return reviewRepository.save(ReviewDto.toEntity(review));
    }

    @Transactional
    public void deleteReviewById(int id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    public void deleteReviewsByProductId(int productId) {
        reviewRepository.deleteReviewsByProductId(productId);
    }
}
