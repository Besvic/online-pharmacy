package com.pharmacy.traning.validator.impl;

import com.pharmacy.traning.validator.ValidatorPharmacy;

public class ValidatorPharmacyImpl implements ValidatorPharmacy {

    private static ValidatorPharmacy instance;

    public static ValidatorPharmacy getInstance() {
        if (instance == null){
            instance = new ValidatorPharmacyImpl();
        }
        return instance;
    }

    private ValidatorPharmacyImpl(){

    }

}
