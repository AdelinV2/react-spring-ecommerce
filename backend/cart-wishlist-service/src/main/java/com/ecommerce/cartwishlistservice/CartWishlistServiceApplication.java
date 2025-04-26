package com.ecommerce.cartwishlistservice;

import com.ecommerce.common.config.OAuth2ClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OAuth2ClientConfig.class)
public class CartWishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartWishlistServiceApplication.class, args);
	}

}
