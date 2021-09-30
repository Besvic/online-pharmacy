package com.pharmacy.traning.model.dao;

public final class ColumnName {

    private ColumnName(){
    }

    //basket table
    public static final String BASKET_ID = "basket_id";
    public static final String BASKET_ORDER_ID = "basket_order_id";
    public static final String BASKET_USER_ID = "basket_user_id";

    //country
    public static final String COUNTRY_ID = "country_id";
    public static final String COUNTRY_NAME = "country_name";

    //manufacture
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
    //public static final String PRODUCT_WAREHOUSE_ID = "product_warehouse_id";
    public static final String PRODUCT_DATE_OF_DELIVERY = "product_date_of_delivery";
    public static final String PRODUCT_MEASURE = "product_measure";
    //public static final String PRODUCT_PHOTO = "product_photo";

    //measure
    public static final String MEASURE_ID = "measure_id";
    public static final String MEASURE_NAME = "measure_name";

    //users
    public static final String USER_ID = "user_id";
    public static final String USER_POSITION = "user_position";
    public static final String USER_NAME = "user_name";
    public static final String USER_CASH = "user_cash";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_STATUS = "user_status";
    public static final String USER_PHOTO = "user_photo";

    //warehouse
    // TODO: 30.08.2021 пока что не использовать, если не буду успевать, то можно удалить так как необходимые поля по количеству и единицам измерения есть в product table
    public static final String WAREHOUSE_ID = "warehouse_id";
    public static final String WAREHOUSE_PRODUCT_ID= "warehouse_product_id";
    public static final String WAREHOUSE_IN_STOCK = "warehouse_in_stock";
    public static final String WAREHOUSE_EMAIL = "warehouse_email";
    public static final String WAREHOUSE_QUANTITY = "warehouse_quantity";
    public static final String WAREHOUSE_UNITS_ID = "warehouse_units_id";





}
