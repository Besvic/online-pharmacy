package com.pharmacy.traning.controller.command;

/**
 * @author Besarab Victor
 * The type Message.
 */
public final class Message {

    private Message(){

    }

    /**
     * The constant ERROR_INPUT_DATA.
     */
    public static final String ERROR_INPUT_DATA = " Check you input data!";
    /**
     * The constant ERROR_INPUT_LOGIN_PASSWORD.
     */
    public static final String ERROR_INPUT_LOGIN_PASSWORD = " Check you login or password!";
    /**
     * The constant ERROR_ADMINISTRATOR_REGISTRATION.
     */
    public static final String ERROR_ADMINISTRATOR_REGISTRATION = "Registration is available only for the first administrator!";
    /**
     * The constant ERROR_PHARMACY_LIST_IS_EMPTY.
     */
    public static final String ERROR_PHARMACY_LIST_IS_EMPTY = "Pick up point list is empty, administrator will add soon.";
    /**
     * The constant ERROR_DELETE.
     */
    public static final String ERROR_DELETE = "Delete function is not available.";
    /**
     * The constant ERROR_RESTORE.
     */
    public static final String ERROR_RESTORE = "Restore function is not available.";
    /**
     * The constant ERROR_LIMITED_ACCESS.
     */
    public static final String ERROR_LIMITED_ACCESS = "Sorry, on this account  limited access some time.";


    /**
     * The constant REPORT_PRODUCT_DELETE.
     */
    public static final String REPORT_PRODUCT_DELETE = "Congratulations, product has been deleted successfully!";
    /**
     * The constant REPORT_DATA_CHANGE.
     */
    public static final String REPORT_DATA_CHANGE = "Congratulations, data has been changed successfully!";
}
