package com.pharmacy.traning.model.entity;

public enum OrderStatus {

    COMPLETED ("completed"),
    NOT_COMPLETED("not_completed");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
