package com.pharmacy.traning.model.entity;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * The type Product.
 */
public class Product {

    private long id;
    private String name;
    private double dosage;
    private String manufactureCountry;
    private int quantity;
    private double price;
    private LocalDate dateOfDelivery;
    private String measure;

    /**
     * Instantiates a new Product.
     *
     * @param id                 the id
     * @param name               the name
     * @param dosage             the dosage
     * @param manufactureCountry the manufacture country
     * @param quantity           the quantity
     * @param price              the price
     * @param dateOfDelivery     the date of delivery
     * @param measure            the measure
     */
    public Product(long id, String name, double dosage, String manufactureCountry, int quantity,
                   double price, LocalDate dateOfDelivery, String measure) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.manufactureCountry = manufactureCountry;
        this.quantity = quantity;
        this.price = price;
        this.dateOfDelivery = dateOfDelivery;
        this.measure = measure;

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
     * Gets dosage.
     *
     * @return the dosage
     */
    public double getDosage() {
        return dosage;
    }

    /**
     * Sets dosage.
     *
     * @param dosage the dosage
     */
    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    /**
     * Gets manufacture country.
     *
     * @return the manufacture country
     */
    public String getManufactureCountry() {
        return manufactureCountry;
    }

    /**
     * Sets manufacture country.
     *
     * @param manufactureCountry the manufacture country
     */
    public void setManufactureCountry(String manufactureCountry) {
        this.manufactureCountry = manufactureCountry;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets date of delivery.
     *
     * @return the date of delivery
     */
    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    /**
     * Sets date of delivery.
     *
     * @param dateOfDelivery the date of delivery
     */
    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    /**
     * Gets measure.
     *
     * @return the measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Sets measure.
     *
     * @param measure the measure
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /***
     *
     * Equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.dosage, dosage) == 0 &&
                manufactureCountry == null ? product.manufactureCountry == null : manufactureCountry.equals(product.manufactureCountry) &&
                quantity == product.quantity && Double.compare(product.price, price) == 0 &&
                measure == null ? product.measure == null : measure.equals(product.measure)  &&
                name == null ? product.name == null : name.equals(product.name) &&
                dateOfDelivery == null ? product.dateOfDelivery == null : dateOfDelivery.equals(product.dateOfDelivery);
    }

    /***
     *
     * Hash Code
     * @return
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(quantity) + Long.hashCode(id) +
                Arrays.hashCode( new double[] {dosage, price}) + name.hashCode() + dateOfDelivery.hashCode() +
                measure.hashCode() + manufactureCountry.hashCode();
    }

    /***
     *
     * To String
     * @return
     */
    @Override
    public String toString() {
        return new String(new StringBuffer().append("Product{")
                .append("id=").append(id)
                .append(", name='").append(name)
                .append(", dosage=").append(dosage)
                .append(", manufactureId=").append(manufactureCountry)
                .append(", quantity=").append(quantity)
                .append(", price=").append(price)
                .append(", dateOfDelivery=").append(dateOfDelivery.toString())
                .append(", measure=").append(measure)
                .append('}'));
    }

    /**
     * The type Product builder.
     */
    public static class ProductBuilder{

        private long id;
        private String name;
        private double dosage;
        private String manufactureCountry;
        private int quantity;
        private double price;
        private LocalDate dateOfDelivery;
        private String measure;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public ProductBuilder setId(long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets dosage.
         *
         * @param dosage the dosage
         * @return the dosage
         */
        public ProductBuilder setDosage(double dosage) {
            this.dosage = dosage;
            return this;
        }

        /**
         * Sets manufacture country.
         *
         * @param manufactureCountry the manufacture country
         * @return the manufacture country
         */
        public ProductBuilder setManufactureCountry(String manufactureCountry) {
            this.manufactureCountry = manufactureCountry;
            return this;
        }

        /**
         * Sets quantity.
         *
         * @param quantity the quantity
         * @return the quantity
         */
        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Sets price.
         *
         * @param price the price
         * @return the price
         */
        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        /**
         * Sets date of delivery.
         *
         * @param dateOfDelivery the date of delivery
         * @return the date of delivery
         */
        public ProductBuilder setDateOfDelivery(LocalDate dateOfDelivery) {
            this.dateOfDelivery = dateOfDelivery;
            return this;
        }

        /**
         * Sets measure.
         *
         * @param measure the measure
         * @return the measure
         */
        public ProductBuilder setMeasure(String measure) {
            this.measure = measure;
            return this;
        }

        /**
         * Create product product.
         *
         * @return the product
         */
        public Product createProduct() {
            return new Product(id, name, dosage, manufactureCountry, quantity, price, dateOfDelivery, measure);
        }
    }
}
