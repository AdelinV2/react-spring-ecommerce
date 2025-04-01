package com.ecommerce.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/**
 * DTO for average rating
 */
public class AverageDto {

    private double average;
    private int count;
}
