package com.pharmacy.traning.model.entity;

import java.time.LocalDate;
import java.util.Arrays;

public class Product {

    private long id;
    private String name;
    private double dosage;
    private String manufactureCountry;
    private int quantity;
    private double price;
    private LocalDate dateOfDelivery;
    private String measure;
    private String photo;

    public Product() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public String getManufactureCountry() {
        return manufactureCountry;
    }

    public void setManufactureCountry(String manufactureCountry) {
        this.manufactureCountry = manufactureCountry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

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

    @Override
    public int hashCode() {
        return Integer.hashCode(quantity) + Long.hashCode(id) +
                Arrays.hashCode( new double[] {dosage, price}) + name.hashCode() + dateOfDelivery.hashCode() +
                measure.hashCode() + manufactureCountry.hashCode();
    }

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
                .append(", measureId=").append(measure)
                .append('}'));
    }

    public static class ProductBuilder{

        private long id;
        private String name;
        private double dosage;
        private String manufactureCountry;
        private int quantity;
        private double price;
        private LocalDate dateOfDelivery;
        private String measure;

        public ProductBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setDosage(double dosage) {
            this.dosage = dosage;
            return this;
        }

        public ProductBuilder setManufactureCountry(String manufactureCountry) {
            this.manufactureCountry = manufactureCountry;
            return this;
        }

        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setDateOfDelivery(LocalDate dateOfDelivery) {
            this.dateOfDelivery = dateOfDelivery;
            return this;
        }

        public ProductBuilder setMeasure(String measure) {
            this.measure = measure;
            return this;
        }

        public Product createProduct() {
            return new Product(id, name, dosage, manufactureCountry, quantity, price, dateOfDelivery, measure);
        }
    }
}
