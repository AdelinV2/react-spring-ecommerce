package com.ecommerce.imageservice.controller;

import com.ecommerce.imageservice.service.AzureBlobStorageService;
import com.ecommerce.imageservice.util.ContainerName;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final AzureBlobStorageService azureBlobStorageService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("containerName") ContainerName containerName,
                              @RequestParam("file") MultipartFile file) throws IOException {

        return azureBlobStorageService.uploadImage(containerName, file);
    }
}
