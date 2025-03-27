package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ProductDto;
import com.ecommerce.product_service.dto.ProductImageDto;
import com.ecommerce.product_service.dto.SpecificationDto;
import com.ecommerce.product_service.dto.SubSpecificationDto;
import com.ecommerce.product_service.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class ProductControllerTestConfig {
        @Bean
        public ProductServiceImpl productService() {
            return Mockito.mock(ProductServiceImpl.class);
        }
    }

    @Test
    public void testCreateProductWithImagesAndSpecifications() throws Exception {

        MockMultipartFile dummyImage = new MockMultipartFile(
                "file",
                "test-image.jpg",
                "image/jpeg",
                "dummy image content".getBytes()
        );

        ProductImageDto imageDto = ProductImageDto.builder()
                .id(1)
                .productId(1)
                .orderIndex((byte) 1)
                .file(dummyImage)
                .build();

        SubSpecificationDto subSpecDto = SubSpecificationDto.builder()
                .id(1)
                .specificationId(1)
                .description("Sub-spec description")
                .value("Sub-spec value")
                .orderIndex((byte) 1)
                .build();

        SpecificationDto specDto = SpecificationDto.builder()
                .id(1)
                .productId(1)
                .title("Specification Title")
                .orderIndex((byte) 1)
                .subSpecifications(List.of(subSpecDto))
                .build();

        ProductDto productDto = ProductDto.builder()
                .id(0)
                .sellerId(1)
                .name("Test Product")
                .category("Test Category")
                .description("A test product with image and specification")
                .price(99.99)
                .oldPrice(129.99)
                .stock(10)
                .weight(1.5)
                .available(true)
                .images(List.of(imageDto))
                .specifications(List.of(specDto))
                .build();

        doNothing().when(productService).createProduct(productDto);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }
}