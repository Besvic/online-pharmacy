package com.pharmacy.traning.model.validator.impl;


import com.pharmacy.traning.model.validator.Validator;

import java.util.Calendar;

import static java.util.Calendar.YEAR;

/**
 * @author Besarab Victor
 * The type Validator.
 */
public class ValidatorImpl implements Validator {

    /**
     * The constant REGEX_CORRECT_EMAIL.
     */
    private static final String REGEX_CORRECT_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    /**
     * The constant REGEX_UPPERCASE_LETTER.
     */
    private static final String REGEX_UPPERCASE_LETTER = "[А-ЯA-Z\s]{1,40}";
    /**
     * The constant REGEX_CORRECT_CVV.
     */
    private static final String REGEX_CORRECT_CVV = "[0-9]{3}";
    /**
     * The constant REGEX_CORRECT_CREDIT_CARD_NUMBER.
     */
    private static final String REGEX_CORRECT_CREDIT_CARD_NUMBER = "[0-9]{16}";
    /**
     * The constant REGEX_CORRECT_DOUBLE.
     */
    private static final String REGEX_CORRECT_DOUBLE = "[0-9]+\\.{0,1}[0-9]{0,6}";
    /**
     * The constant REGEX_CORRECT_MONEY.
     */
    private static final String REGEX_CORRECT_MONEY = "[0-9]+\\.{0,1}[0-9]{0,2}";
    /**
     * The constant REGEX_ONLY_LETTER.
     */
    private static final String REGEX_ONLY_LETTER = "[а-яА-Яa-zA-Z]{1,40}";
    /**
     * The constant REGEX_CORRECT_NAME.
     */
    private static final String REGEX_CORRECT_NAME = "[а-яa-zА-ЯA-Z\s]{1,40}";
    /**
     * The constant REGEX_CORRECT_INT.
     */
    private static final String REGEX_CORRECT_INT = "[0-9]{1,6}";
    /**
     * The constant REGEX_ONLY_NUMBER.
     */
    private static final String REGEX_ONLY_NUMBER = "[0-9]{1,10}";
    /**
     * The constant REGEX_CORRECT_MONTH.
     */
    private static final String REGEX_CORRECT_MONTH = "[0-9]{1,2}";
    /**
     * The constant REGEX_CORRECT_YEAR.
     */
    private static final String REGEX_CORRECT_YEAR = "[0-9]{4}";

    private static ValidatorImpl instance;

    /**
     * Get instance validator.
     *
     * @return the validator
     */
    public static ValidatorImpl getInstance(){
        if (instance == null)
            instance = new ValidatorImpl();
        return instance;
    }

    private ValidatorImpl(){

    }

    @Override
    public boolean isPassword(String string) {
        return string != null && string.length() >= 6 && !string.matches(REGEX_ONLY_LETTER) &&
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

    @Override
    public boolean isName(String string) {
        return string != null && string.matches(REGEX_CORRECT_NAME);
    }
}
