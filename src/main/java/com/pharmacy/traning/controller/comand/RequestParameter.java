package com.pharmacy.traning.controller.comand;

public final class RequestParameter {

    //command
    public static final String COMMAND = "command";

    //profile data
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String NEW_PASSWORD = "new_password";
    public static final String NEW_REPEAT_PASSWORD = "new_repeat_password";
    public static final String NEW_NAME = "new_name";

    //product data
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String MANUFACTURE_COUNTRY = "manufacture_country";
    public static final String DOSAGE = "dosage";
    public static final String MEASURE = "measure";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String DATE = "date";


    public static final String ORDER_ID = "order_id";
    public static final String PHARMACY_ID = "pharmacy_id";



    //locale
    public static final String LOCALE = "locale";

    //registration
    public static final String NAME = "name";
    public static final String IS_ADMIN = "is_admin";

    //verification
    public static final String USER_ID = "user_id";

    private RequestParameter() {
    }
}
