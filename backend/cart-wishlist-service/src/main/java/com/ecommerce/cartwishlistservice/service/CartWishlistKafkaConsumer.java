package com.ecommerce.cartwishlistservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartWishlistKafkaConsumer {

    private final CartProductService cartProductService;
    private final WishlistProductService wishlistProductService;
    private final JwtDecoder jwtDecoder;
    private final ObjectMapper objectMapper;

    private void validateToken(String message) throws Exception {

        JsonNode node = objectMapper.readTree(message);

        if (!node.has("authToken") || node.get("authToken").asText().isEmpty()) {
            throw new RuntimeException("Unauthorized message");
        }

        jwtDecoder.decode(node.get("authToken").asText());
    }

    @KafkaListener(topics = "product-deleted-topic", groupId = "cart-wishlist-service")
    public void consumeProductDeleted(String message) {

        try {
            validateToken(message);

            int productId = objectMapper.readTree(message).get("productId").asInt();

            cartProductService.deleteCartProductsByProductId(productId);
            wishlistProductService.deleteWishlistsByProductId(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
