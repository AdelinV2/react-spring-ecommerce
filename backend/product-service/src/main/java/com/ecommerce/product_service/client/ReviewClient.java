package com.ecommerce.product_service.client;

import com.ecommerce.product_service.dto.AverageReviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "review-service", url = "${REVIEW_SERVICE_URL:http://localhost:8000/api/review/}")
public interface ReviewClient {

    @GetMapping("product-average/{productId}")
    AverageReviewDto getProductAverage(@PathVariable("productId") int productId);
}
