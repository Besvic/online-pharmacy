package com.pharmacy.traning.model.entity;

/**
 * @author Besarab Victor
 * The enum Pharmacy status.
 */
public enum PharmacyStatus {
    /**
     * Delete pharmacy status.
     */
    DELETE("delete"),
    /**
     * Actual pharmacy status.
     */
    ACTUAL("actual");

    private String value;

    PharmacyStatus(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
