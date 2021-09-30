package com.pharmacy.traning.model.entity;

public enum PharmacyStatus {
    DELETE("delete"),
    ACTUAL("actual");

    private String value;

    PharmacyStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
