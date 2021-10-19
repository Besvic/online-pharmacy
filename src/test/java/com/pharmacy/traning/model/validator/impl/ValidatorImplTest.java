package com.pharmacy.traning.model.validator.impl;


import com.pharmacy.traning.model.validator.Validator;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorImplTest {

    private static final Validator valid = ValidatorImpl.getInstance();

    // Correct result

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
        boolean actual = valid.isOnlyLetter("фывсыв");
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

    @Test
    void isName() {
        boolean actual = valid.isName("вапр роро");
        assertTrue(actual);
    }

    @Test
    void isDate() {
        boolean actual = valid.isDate("2021-10-06");
        assertTrue(actual);
    }

    // Incorrect result

    @Test
    public void isPasswordFalse() {
        boolean actual = valid.isPassword("gfdsxa");
        assertFalse(actual);
    }

    @Test
    public void emailIsValidFalse() {
        boolean actual = valid.isEmail("asdfgmail.ru");
        assertFalse(actual);

    }

    @Test
    void isOnlyLetterFalse() {
        boolean actual = valid.isOnlyLetter("фыв454сыв");
        assertFalse(actual);
    }

    @Test
    void isOnlyNumberFalse() {
        boolean actual = valid.isOnlyNumber("1234ddf0");
        assertFalse(actual);
    }

    @Test
    void isOnlyUpperCaseLetterFalse() {
        boolean actual = valid.isOnlyUpperCaseLetter("DFGsdHJKIUYTGVB");
        assertFalse(actual);
    }

    @Test
    void isCvvFalse() {
        boolean actual = valid.isCvv("2323");
        assertFalse(actual);
    }

    @Test
    void isCreditCodeFalse() {
        boolean actual = valid.isCreditCode("06789123456");
        assertFalse(actual);
    }

    @Test
    void isDoubleFalse() {
        boolean actual = valid.isDouble("10d0212");
        assertFalse(actual);
    }

    @Test
    void isIntFalse() {
        boolean actual = valid.isInt("6d543");
        assertFalse(actual);
    }

    @Test
    void isMoneyFalse() {
        boolean actual = valid.isMoney("234.3224");
        assertFalse(actual);
    }

    @Test
    void isYearFalse() {
        boolean actual = valid.isYear("20c1");
        assertFalse(actual);
    }

    @Test
    void isMonthFalse() {
        boolean actual = valid.isMonth("21");
        assertFalse(actual);
    }

    @Test
    void isNameFalse() {
        boolean actual = valid.isName("dfv8");
        assertFalse(actual);
    }


    @Test
    void isDateFalse() {
        boolean actual = valid.isDate("2021/10/06");
        assertFalse(actual);
    }
}