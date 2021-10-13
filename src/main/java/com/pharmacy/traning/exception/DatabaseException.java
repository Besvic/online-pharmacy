package com.pharmacy.traning.exception;

/**
 * The type Database exception.
 */
public class DatabaseException extends Exception{

    /**
     * Instantiates a new Database exception.
     */
    public DatabaseException() {
    }

    /**
     * Instantiates a new Database exception.
     *
     * @param message the message
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Database exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Database exception.
     *
     * @param cause the cause
     */
    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
