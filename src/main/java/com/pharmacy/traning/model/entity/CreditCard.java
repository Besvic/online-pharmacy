package com.pharmacy.traning.model.entity;

import java.util.Arrays;
import java.util.Objects;

public class CreditCard {

    private String number;
    private String name;
    private int month;
    private int year;
    private int cvv;
    private double cash;

    public CreditCard() {
    }

    public CreditCard(String number, String name, int month, int year, int cvv, double cash) {
        this.number = number;
        this.name = name;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.cash = cash;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreditCard that = (CreditCard) o;
        return month == that.month && year == that.year && cvv == that.cvv && cash == that.cash &&
                number == null ? that.number == null : number.equals(that.number) && name == null ? that.name == null : name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode( new int[] {month, year, cvv}) + Double.hashCode(cash) + number.hashCode() + name.hashCode();
    }

    public static class CreditCardBuilder{

        private String number;
        private String name;
        private int month;
        private int year;
        private int cvv;
        private double cash;

        public CreditCardBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public CreditCardBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CreditCardBuilder setMonth(int month) {
            this.month = month;
            return this;
        }

        public CreditCardBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public CreditCardBuilder setCvv(int cvv) {
            this.cvv = cvv;
            return this;
        }

        public CreditCardBuilder setCash(double cash) {
            this.cash = cash;
            return this;
        }

        public CreditCard createCreditCard() {
            return new CreditCard(number, name, month, year, cvv, cash);
        }
    }
}
