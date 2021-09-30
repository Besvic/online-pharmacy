package com.pharmacy.traning.model.entity;

import java.util.Objects;

public class Pharmacy {

    private long id;
    private String city;
    private String street;
    private int number;
    private PharmacyStatus status;

    public Pharmacy() {
    }

    public Pharmacy(long id, String city, String street, int number, PharmacyStatus status) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PharmacyStatus getStatus() {
        return status;
    }

    public void setStatus(PharmacyStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pharmacy pharmacy = (Pharmacy) o;
        return id == pharmacy.id && number == pharmacy.number && city == null ? pharmacy.city == null : city.equals(pharmacy.city)
                && (street == null ? pharmacy.street == null : street.equals(pharmacy.street)) && street == null ?
                pharmacy.street == null : street.equals(pharmacy.status);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) +  city.hashCode() + street.hashCode() + Integer.hashCode(number) + status.hashCode();
    }

    @Override
    public String toString() {
        return new String(new StringBuilder()
                .append("Pharmacy{ id=").append(id)
                .append(", city='").append(city)
                .append(", street='").append(street)
                .append(", number=").append(number)
                .append( ", status=").append(status)
                .append('}'));
    }

    public static class PharmacyBuilder{

        private long id;
        private String city;
        private String street;
        private int number;
        private PharmacyStatus status;

        public PharmacyBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public PharmacyBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public PharmacyBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public PharmacyBuilder setNumber(int number) {
            this.number = number;
            return this;
        }

        public PharmacyBuilder setStatus(PharmacyStatus status) {
            this.status = status;
            return this;
        }

        public Pharmacy createPharmacy() {
            return new Pharmacy(id, city, street, number, status);
        }
    }
}
