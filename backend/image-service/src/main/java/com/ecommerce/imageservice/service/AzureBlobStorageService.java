package com.ecommerce.imageservice.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.ecommerce.imageservice.util.ContainerName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AzureBlobStorageService {

    private final BlobServiceClient blobServiceClient;

    public String uploadImage(ContainerName containerName, String fileName, MultipartFile file) throws IOException {

        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName.getContainerName()).getBlobClient(fileName);

        blobClient.upload(file.getInputStream(), file.getSize(), true);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(file.getContentType()));

        return blobClient.getBlobUrl();
    }
}
