package com.ecommerce.imageservice.util;

public enum ContainerName {
    PRODUCT("product-images-container"),
    PROFILE("profile-images-container");

    private final String containerName;

    ContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getContainerName() {
        return containerName;
    }
}
