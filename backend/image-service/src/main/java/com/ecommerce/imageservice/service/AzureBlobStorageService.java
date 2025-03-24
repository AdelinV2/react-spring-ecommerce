package com.ecommerce.imageservice.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.ListBlobsOptions;
import com.ecommerce.imageservice.util.ContainerName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AzureBlobStorageService {

    private final BlobServiceClient blobServiceClient;

    public String uploadImage(ContainerName containerName, MultipartFile file) throws IOException {

        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName.getContainerName()).getBlobClient(file.getName());

        blobClient.upload(file.getInputStream(), file.getSize(), true);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(file.getContentType()));

        return blobClient.getBlobUrl();
    }

    public void deleteImages(ContainerName containerName, String id) {

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName.getContainerName());

        ListBlobsOptions options = new ListBlobsOptions().setPrefix(id + "_");

        for (BlobItem blobItem : containerClient.listBlobs(options, Duration.ofSeconds(30))) {
            BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
            blobClient.delete();
        }
    }
}
