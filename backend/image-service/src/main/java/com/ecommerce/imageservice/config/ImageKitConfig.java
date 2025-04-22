package com.ecommerce.imageservice.config;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ImageKitConfig {

    @Bean
    public ImageKit imageKit(
            @Value("${imagekit.publicKey}") String publicKey,
            @Value("${imagekit.privateKey}") String privateKey,
            @Value("${imagekit.urlEndpoint}") String urlEndpoint) {

        ImageKit imageKit = ImageKit.getInstance();
        imageKit.setConfig(new Configuration(publicKey, privateKey, urlEndpoint));

        return imageKit;
    }
}
