package com.ecommerce.userservice.enums;

public enum Role {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    SELLER("SELLER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
