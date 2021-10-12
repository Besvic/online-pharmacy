package com.pharmacy.traning.model.entity;

import java.util.Arrays;

/**
 * The type Credit card.
 */
public class CreditCard {

    private String number;
    private String name;
    private int month;
    private int year;
    private int cvv;
    private double cash;

    /**
     * Instantiates a new Credit card.
     *
     * @param number the number
     * @param name   the name
     * @param month  the month
     * @param year   the year
     * @param cvv    the cvv
     * @param cash   the cash
     */
    public CreditCard(String number, String name, int month, int year, int cvv, double cash) {
        this.number = number;
        this.name = name;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.cash = cash;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets cvv.
     *
     * @return the cvv
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * Sets cvv.
     *
     * @param cvv the cvv
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * Gets cash.
     *
     * @return the cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * Sets cash.
     *
     * @param cash the cash
     */
    public void setCash(double cash) {
        this.cash = cash;
    }

    /***
     * Equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreditCard that = (CreditCard) o;
        return month == that.month && year == that.year && cvv == that.cvv && cash == that.cash &&
                number == null ? that.number == null : number.equals(that.number) &&
                name == null ? that.name == null : name.equals(that.name);
    }

    /***
     * Hash Code
     * @return
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode( new int[] {month, year, cvv}) + Double.hashCode(cash) + number.hashCode() + name.hashCode();
    }

    /***
     * To String
     * @return
     */
    @Override
    public String toString() {
        return new String(new StringBuilder()
                .append("CreditCard{")
                .append("number='").append(number)
                .append(", name='").append(name)
                .append(", month=").append(month)
                .append(", year=").append(year)
                .append(", cvv=").append(cvv)
                .append(", cash=").append(cash)
                .append('}'));
    }

    /**
     * The type Credit card builder.
     */
    public static class CreditCardBuilder{

        private String number;
        private String name;
        private int month;
        private int year;
        private int cvv;
        private double cash;

        /**
         * Sets number.
         *
         * @param number the number
         * @return the number
         */
        public CreditCardBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public CreditCardBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets month.
         *
         * @param month the month
         * @return the month
         */
        public CreditCardBuilder setMonth(int month) {
            this.month = month;
            return this;
        }

        /**
         * Sets year.
         *
         * @param year the year
         * @return the year
         */
        public CreditCardBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        /**
         * Sets cvv.
         *
         * @param cvv the cvv
         * @return the cvv
         */
        public CreditCardBuilder setCvv(int cvv) {
            this.cvv = cvv;
            return this;
        }

        /**
         * Sets cash.
         *
         * @param cash the cash
         * @return the cash
         */
        public CreditCardBuilder setCash(double cash) {
            this.cash = cash;
            return this;
        }

        /**
         * Create credit card credit card.
         *
         * @return the credit card
         */
        public CreditCard createCreditCard() {
            return new CreditCard(number, name, month, year, cvv, cash);
        }
    }
}
