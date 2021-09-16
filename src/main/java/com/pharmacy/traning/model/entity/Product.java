package com.pharmacy.traning.model.entity;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Arrays;

public class Product {

    private long id;
    private String name;
    private double dosage;
    private long manufactureId;
    private int quantity;
    private double price;
    private LocalDate dateOfDelivery;
    private long measureId;
    private Blob photo;

    public Product() {
    }

    public Product(long id, String name, double dosage, long manufactureId, int quantity,
                   double price, LocalDate dateOfDelivery, long measureId, Blob photo) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.manufactureId = manufactureId;
        this.quantity = quantity;
        this.price = price;
        this.dateOfDelivery = dateOfDelivery;
        this.measureId = measureId;
        this.photo = photo;
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

    public long getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(long manufactureId) {
        this.manufactureId = manufactureId;
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

    public long getMeasureId() {
        return measureId;
    }

    public void setMeasureId(long measureId) {
        this.measureId = measureId;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.dosage, dosage) == 0 && manufactureId == product.manufactureId &&
                quantity == product.quantity && Double.compare(product.price, price) == 0 && measureId == product.measureId &&
                name.equals(product.name) && dateOfDelivery.equals(product.dateOfDelivery) && photo.equals(product.photo);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(quantity) + Arrays.hashCode(new long[] {id, manufactureId, measureId}) +
                Arrays.hashCode( new double[] {dosage, price}) + name.hashCode() + dateOfDelivery.hashCode() +
                photo.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuffer().append("Product{")
                .append("id=").append(id)
                .append(", name='").append(name)
                .append(", dosage=").append(dosage)
                .append(", manufactureId=").append(manufactureId)
                .append(", quantity=").append(quantity)
                .append(", price=").append(price)
                .append(", dateOfDelivery=").append(dateOfDelivery.toString())
                .append(", measureId=").append(measureId)
                .append(", photo=").append(photo.toString())
                .append('}')
                .toString();
    }

    public static class ProductBuilder{

        private long id;
        private String name;
        private double dosage;
        private long manufactureId;
        private int quantity;
        private double price;
        private LocalDate dateOfDelivery;
        private long unitsId;
        private Blob photo;

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

        public ProductBuilder setManufactureId(long manufactureId) {
            this.manufactureId = manufactureId;
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

        public ProductBuilder setUnitsId(long unitsId) {
            this.unitsId = unitsId;
            return this;
        }

        public ProductBuilder setPhoto(Blob photo) {
            this.photo = photo;
            return this;
        }

        public Product createProduct() {
            return new Product(id, name, dosage, manufactureId, quantity, price, dateOfDelivery, unitsId, photo);
        }
    }
}
