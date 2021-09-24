package com.pharmacy.traning.validator;

public interface ValidatorUser extends Validator {

    boolean passwordLengthIsValid(String password);
    boolean emailIsValid(String email);

}
