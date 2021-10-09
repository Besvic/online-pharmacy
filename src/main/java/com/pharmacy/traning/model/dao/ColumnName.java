package com.pharmacy.traning.model.dao;

public final class ColumnName {

    private ColumnName(){
    }

    public static final String PHARMACY_ID = "pharmacy_id";
    public static final String PHARMACY_CITY= "pharmacy_city";
    public static final String PHARMACY_STREET = "pharmacy_street";
    public static final String PHARMACY_NUMBER = "pharmacy_number";
    public static final String PHARMACY_STATUS = "pharmacy_status";

    //order
    public static final String ORDER_ID= "order_id";
    public static final String ORDER_PRODUCT_ID = "order_product_id";
    public static final String ORDER_USER_ID = "order_user_id";
    public static final String ORDER_STATUS = "order_status";
    public static final String ORDER_QUANTITY = "order_quantity";
    public static final String ORDER_PHARMACY_ID = "order_pharmacy_id";
    public static final String ORDER_DATE = "order_date";

    //product
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_DOSAGE = "product_dosage";
    public static final String PRODUCT_MANUFACTURE = "product_manufacture";
    public static final String PRODUCT_QUANTITY = "product_quantity";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_DATE_OF_DELIVERY = "product_date_of_delivery";
    public static final String PRODUCT_MEASURE = "product_measure";

    //users
    public static final String USER_ID = "user_id";
    public static final String USER_POSITION = "user_position";
    public static final String USER_NAME = "user_name";
    public static final String USER_CASH = "user_cash";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_STATUS = "user_status";
    public static final String USER_PHOTO = "user_photo";

}
