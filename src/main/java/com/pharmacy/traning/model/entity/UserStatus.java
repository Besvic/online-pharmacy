package com.pharmacy.traning.model.entity;

/**
 * @author Besarab Victor
 * The enum User status.
 */
public enum UserStatus {
    /**
     * In register user status.
     */
    IN_REGISTER ("in_register"),
    /**
     * Active user status.
     */
    ACTIVE ("active"),
    /**
     * Delete user status.
     */
    DELETE("delete");

    private final String value;

    UserStatus(String value) {
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
