package com.pharmacy.traning.validator.impl;


import com.pharmacy.traning.validator.Validator;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorImplTest {

    private static final Validator valid = ValidatorImpl.getInstance();

    @Test
    public void isPassword() {
        boolean actual = valid.isPassword("SDF23sdfv456");
        assertTrue(actual);
    }

    @Test
    public void emailIsValid() {
        boolean actual = valid.isEmail("asdfg@mail.ru");
        assertTrue(actual);

    }

    @Test
    void isOnlyLetter() {
        boolean actual = valid.isOnlyLetter("dsdfvs");
        assertTrue(actual);
    }

    @Test
    void isOnlyNumber() {
        boolean actual = valid.isOnlyNumber("1234567890");
        assertTrue(actual);
    }

    @Test
    void isOnlyUpperCaseLetter() {
        boolean actual = valid.isOnlyUpperCaseLetter("DFGHJKIUYTGVB");
        assertTrue(actual);
    }

    @Test
    void isCvv() {
        boolean actual = valid.isCvv("123");
        assertTrue(actual);
    }

    @Test
    void isCreditCode() {
        boolean actual = valid.isCreditCode("0123456789123456");
        assertTrue(actual);
    }

    @Test
    void isDouble() {
        boolean actual = valid.isDouble("102.10212");
        assertTrue(actual);
    }

    @Test
    void isInt() {
        boolean actual = valid.isInt("6543");
        assertTrue(actual);
    }

    @Test
    void isMoney() {
        boolean actual = valid.isMoney("234.34");
        assertTrue(actual);
    }

    @Test
    void isYear() {
        boolean actual = valid.isYear("2021");
        assertTrue(actual);
    }

    @Test
    void isMonth() {
        boolean actual = valid.isMonth("2");
        assertTrue(actual);
    }

    // TODO: 07.10.2021  create test for false result
}