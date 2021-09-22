package com.pharmacy.traning.validator;

public interface ValidatorUser {

    boolean passwordLengthIsValid(String password);
    boolean emailIsValid(String email);
    boolean nameIsValid(String name);

}
