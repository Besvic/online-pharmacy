package com.pharmacy.traning.model.validator;

/**
 * @author Besarab Victor
 * The interface Validator for search incorrect input data.
 */
public interface Validator {
    /**
     * Is password boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isPassword(String string);

    /**
     * Is email boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isEmail(String string);

    /**
     * Is only letter boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isOnlyLetter(String string);

    /**
     * Is only number boolean.
     *
     * @param number the number
     * @return the boolean
     */
    boolean isOnlyNumber(String number);

    /**
     * Is only upper case letter boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isOnlyUpperCaseLetter(String string);

    /**
     * Is cvv boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isCvv(String string);

    /**
     * Is credit code boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isCreditCode(String string);

    /**
     * Is double boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isDouble(String string);

    /**
     * Is int boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isInt(String string);

    /**
     * Is money boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isMoney(String string);

    /**
     * Is year boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isYear(String string);

    /**
     * Is month boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isMonth(String string);

    /**
     * Is name boolean.
     *
     * @param string the string
     * @return the boolean
     */
    boolean isName(String string);

    /**
     *
     * @param string the string
     * @return the boolean
     */
    boolean isDate(String string);

}
