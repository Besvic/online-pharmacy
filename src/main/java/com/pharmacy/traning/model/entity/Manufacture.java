package com.pharmacy.traning.model.entity;

import java.util.Arrays;

public class Manufacture {

    private long id;
    private String name;
    private String email;
    private long countryId;

    public Manufacture() {
    }

    public Manufacture(long id, String name, String email, long countryId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.countryId = countryId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Manufacture other = (Manufacture) o;
        return id == other.id && countryId == other.countryId && name.equals(other.name) && email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new long[] {id, countryId}) + name.hashCode() + email.hashCode();
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", countryId=" + countryId +
                '}';
    }

    public static class ManufactureBuilder{

        private long id;
        private String name;
        private String email;
        private long countryId;

        public ManufactureBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public ManufactureBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ManufactureBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ManufactureBuilder setCountryId(long countryId) {
            this.countryId = countryId;
            return this;
        }

        public Manufacture createManufacture() {
            return new Manufacture(id, name, email, countryId);
        }
    }
}
