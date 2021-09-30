package com.pharmacy.traning.model.entity;

public enum UserStatus {
    IN_REGISTER ("in_register"),
    ACTIVE ("active"),
    DELETE("delete");

    private final String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
