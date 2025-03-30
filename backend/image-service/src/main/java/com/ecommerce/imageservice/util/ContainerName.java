package com.ecommerce.imageservice.util;

public enum ContainerName {
    PRODUCT("product-image-container"),
    PROFILE("profile-image-container");

    private final String containerName;

    ContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getContainerName() {
        return containerName;
    }
}
