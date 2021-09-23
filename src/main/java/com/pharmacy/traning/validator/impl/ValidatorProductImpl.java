package com.pharmacy.traning.validator.impl;

import com.pharmacy.traning.validator.Validator;

public class ValidatorProductImpl implements Validator {


    private static ValidatorProductImpl instance;

    public static ValidatorProductImpl getInstance() {
        if (instance == null)
            instance = new ValidatorProductImpl();
        return instance;
    }

    private ValidatorProductImpl(){

    }
}
