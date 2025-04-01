package com.ecommerce.reviewservice.repository;

import com.ecommerce.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

  List<Review> findByProductId(int productId);

  void deleteReviewsByProductId(int productId);
}