package com.pharmacy.traning.validator;

public interface Validator {

    String REGEX_LETTER = "[а-яa-zА-ЯA-Z]+";
    String REGEX_NUMBER = "[1-9]+";

    default boolean isNullObject(Object object){
        return object != null;
    }
    default boolean isOnlyLetter(String string){

        return string.matches(REGEX_LETTER);
    }
    default boolean isOnlyNumber(Integer number){
        String num = String.valueOf(number);
        return num.matches(REGEX_NUMBER);
    }

}
