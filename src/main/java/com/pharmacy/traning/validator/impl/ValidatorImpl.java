package com.pharmacy.traning.validator.impl;


import com.pharmacy.traning.validator.Validator;
import com.pharmacy.traning.validator.ValidatorUser;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.Calendar;

import static java.util.Calendar.YEAR;

public class ValidatorImpl implements Validator {

    private static final String REGEX_CORRECT_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String REGEX_UPPERCASE_LETTER = "[А-ЯA-Z]+";
    private static final String REGEX_CORRECT_CVV = "[0-9]{3}";
    private static final String REGEX_CORRECT_CREDIT_CARD_NUMBER = "[0-9]{16,19}";
    private static final String REGEX_CORRECT_DOUBLE = "[0-9]+\\.{0,1}[0-9]{0,6}";
    private static final String REGEX_CORRECT_MONEY = "[0-9]+\\.{0,1}[0-9]{0,2}";
    private static final String REGEX_ONLY_LETTER = "[а-яa-zА-ЯA-Z]+";
    private static final String REGEX_CORRECT_INT = "[0-9]{1,6}";
    private static final String REGEX_ONLY_NUMBER = "[0-9]+";
    private static final String REGEX_CORRECT_MONTH = "[0-9]{1,2}";
    private static final String REGEX_CORRECT_YEAR = "[0-9]{4}";

    private static ValidatorImpl instance;

    public static ValidatorImpl getInstance(){
        if (instance == null)
            instance = new ValidatorImpl();
        return instance;
    }

    private ValidatorImpl(){

    }

    @Override
    public boolean isPassword(String string) {
        return string != null && string.length() > 8 && !string.matches(REGEX_ONLY_LETTER) &&
                !string.matches(REGEX_ONLY_NUMBER);
    }

    @Override
    public boolean isEmail(String email) {
        return email != null && email.matches(REGEX_CORRECT_EMAIL);
    }

    @Override
    public boolean isOnlyLetter(String string) {
        return string != null && string.matches(REGEX_ONLY_LETTER);
    }

    @Override
    public boolean isOnlyNumber(String number) {
        return number != null && number.matches(REGEX_ONLY_NUMBER);
    }

    @Override
    public boolean isOnlyUpperCaseLetter(String string) {
        return string != null && string.matches(REGEX_UPPERCASE_LETTER);
    }

    @Override
    public boolean isCvv(String string) {
        return string != null && string.matches(REGEX_CORRECT_CVV);
    }

    @Override
    public boolean isCreditCode(String string) {
        return string != null && string.matches(REGEX_CORRECT_CREDIT_CARD_NUMBER);
    }

    @Override
    public boolean isDouble(String string) {
        return string != null && string.matches(REGEX_CORRECT_DOUBLE);
    }

    @Override
    public boolean isInt(String string) {
        return string != null && string.matches(REGEX_CORRECT_INT);
    }

    @Override
    public boolean isMoney(String string) {
        return string != null && string.matches(REGEX_CORRECT_MONEY);
    }

    @Override
    public boolean isYear(String string) {
        int currentYear = Calendar.getInstance().get(YEAR);
        return string != null && string.matches(REGEX_CORRECT_YEAR) && Integer.parseInt(string) >= currentYear;
    }

    @Override
    public boolean isMonth(String string) {
        return string != null && string.matches(REGEX_CORRECT_MONTH) && Integer.parseInt(string) <= 12 && Integer.parseInt(string) > 0;
    }
}
