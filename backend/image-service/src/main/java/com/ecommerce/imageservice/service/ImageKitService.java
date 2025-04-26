package com.ecommerce.imageservice.service;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.GetFileListRequest;
import io.imagekit.sdk.models.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageKitService {

    private final ImageKit imageKit;

    public Result uploadProductImage(String base64, String fileName) throws Exception {

        FileCreateRequest request = new FileCreateRequest(base64, fileName);
        request.setFolder("/products/");

        return imageKit.upload(request);
    }

    public Result uploadUserImage(String base64, String fileName) throws Exception {

        FileCreateRequest request = new FileCreateRequest(base64, fileName);
        request.setFolder("/users/");

        return imageKit.upload(request);
    }

    public void deleteProductImagesByProductId(String productId) throws Exception {

        String prefix = "/products/" + productId + "_";
        GetFileListRequest request = new GetFileListRequest();
        request.setSearchQuery("name^=\"" + prefix + "\"");

        var resultList = imageKit.getFileList(request);

        for (var result : resultList.getResults()) {
            imageKit.deleteFile(result.getFileId());
        }
    }

    public void deleteUserImagesByUserId(String userId) throws Exception {

        String prefix = "/users/" + userId + "_";
        GetFileListRequest request = new GetFileListRequest();
        request.setSearchQuery("name^=\"" + prefix + "\"");

        var resultList = imageKit.getFileList(request);

        for (var result : resultList.getResults()) {
            imageKit.deleteFile(result.getFileId());
        }
    }
}