package com.pharmacy.traning.validator;

public interface Validator {

    String REGEX_LETTER = "[а-яa-zА-ЯA-Z]+";
    String REGEX_NUMBER = "[1-9]+";

    default boolean isNullObject(Object object){
        return object != null;
    }

    boolean isPassword(String string);
    boolean isEmail(String string);
    boolean isOnlyLetter(String string);
    boolean isOnlyNumber(String number);
    boolean isOnlyUpperCaseLetter(String string);
    boolean isCvv(String string);
    boolean isCreditCode(String string);
    boolean isDouble(String string);
    boolean isInt(String string);
    boolean isMoney(String string);
    boolean isYear(String string);
    boolean isMonth(String string);
    boolean isName(String string);



}
