package com.example.online_pharmacy.entity;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Product {

    private long id;
    private String name;
    private double dosage;
    private long manufactureId;
    private int quantity;
    private double price;
    private LocalDate dateOfDelivery;
    private long unitsId;
    private Blob photo;

    public Product() {
    }

    public Product(long id, String name, double dosage, long manufactureId, int quantity,
                   double price, LocalDate dateOfDelivery, long unitsId, Blob photo) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.manufactureId = manufactureId;
        this.quantity = quantity;
        this.price = price;
        this.dateOfDelivery = dateOfDelivery;
        this.unitsId = unitsId;
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

    public long getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(long unitsId) {
        this.unitsId = unitsId;
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
                quantity == product.quantity && Double.compare(product.price, price) == 0 && unitsId == product.unitsId &&
                name.equals(product.name) && dateOfDelivery.equals(product.dateOfDelivery) && photo.equals(product.photo);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(quantity) + Arrays.hashCode(new long[] {id, manufactureId, unitsId}) +
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
                .append(", unitsId=").append(unitsId)
                .append(", photo=").append(photo.toString())
                .append('}')
                .toString();
    }
}
