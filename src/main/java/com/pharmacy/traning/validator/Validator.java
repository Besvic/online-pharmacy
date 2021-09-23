package com.pharmacy.traning.validator;

public interface Validator {

    String REGEX_LETTER = "[а-яa-zА-ЯA-Z]+";

    default boolean isNullObject(Object object){
        return object != null;
    }
    default boolean isOnlyLetter(String string){

        return string.matches(REGEX_LETTER);
    }

}
