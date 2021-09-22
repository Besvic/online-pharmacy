package com.pharmacy.traning.model.entity;

public enum UserStatus {
    IN_REGISTER ("in_register"),
    ACTION ("action");

    private final String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
