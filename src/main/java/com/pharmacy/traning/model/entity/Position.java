package com.pharmacy.traning.model.entity;

public enum Position {
    USER ("user"),
    ADMIN ("admin"),
    DELETE ("delete");

    private final String value;

    Position(String value) {
        this.value = value;
    }
}
