package com.pharmacy.traning.validator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Validator {

    default boolean isNull(Object object){
        return object == null;
    }
}
