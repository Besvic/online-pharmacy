package com.pharmacy.traning.validator.impl;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserValidatorImplTest {

    @Test
    public void passwordLengthIsValid() {
    }

    @Test
    public void emailIsValid() {
        String email = "asdfg@mail.ru";
        boolean expect = true;
        assertEquals(expect, UserValidatorImpl.getInstance().emailIsValid(email));

    }

    @Test
    public void nameIsValid() {
    }
}