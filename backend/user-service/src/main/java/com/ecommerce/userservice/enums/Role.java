package com.ecommerce.userservice.enums;

public enum Role {
    ADMIN("admin"),
    CUSTOMER("customer"),
    SELLER("seller");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
