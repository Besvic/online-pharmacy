package com.pharmacy.traning.validator.impl;


import com.pharmacy.traning.validator.ValidatorUser;
import org.apache.commons.validator.routines.EmailValidator;

public class UserValidatorImpl implements ValidatorUser {

    private static UserValidatorImpl instance;

    public static UserValidatorImpl getInstance(){
        if (instance == null)
            instance = new UserValidatorImpl();
        return instance;
    }

    private UserValidatorImpl(){

    }

    @Override
    public boolean passwordLengthIsValid(String password) {
        return false;
    }

    @Override
    public boolean emailIsValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
