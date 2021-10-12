package com.pharmacy.traning.model.entity;

/**
 * The type Pharmacy.
 */
public class Pharmacy {

    private long id;
    private String city;
    private String street;
    private int number;
    private PharmacyStatus status;

    /**
     * Instantiates a new Pharmacy.
     *
     * @param id     the id
     * @param city   the city
     * @param street the street
     * @param number the number
     * @param status the status
     */
    public Pharmacy(long id, String city, String street, int number, PharmacyStatus status) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.status = status;
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
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public PharmacyStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(PharmacyStatus status) {
        this.status = status;
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
        Pharmacy pharmacy = (Pharmacy) o;
        return id == pharmacy.id && number == pharmacy.number && city == null ? pharmacy.city == null : city.equals(pharmacy.city)
                && street == null ? pharmacy.street == null : street.equals(pharmacy.street)
                && status == null ? pharmacy.status == null : status.equals(pharmacy.status);
    }

    /***
     * Hash Code
     * @return
     */
    @Override
    public int hashCode() {
        return Long.hashCode(id) +  city.hashCode() + street.hashCode() + Integer.hashCode(number) + status.hashCode();
    }

    /***
     *
     * To String
     * @return
     */
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

    /**
     * The type Pharmacy builder.
     */
    public static class PharmacyBuilder{

        private long id;
        private String city;
        private String street;
        private int number;
        private PharmacyStatus status;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public PharmacyBuilder setId(long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets city.
         *
         * @param city the city
         * @return the city
         */
        public PharmacyBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets street.
         *
         * @param street the street
         * @return the street
         */
        public PharmacyBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        /**
         * Sets number.
         *
         * @param number the number
         * @return the number
         */
        public PharmacyBuilder setNumber(int number) {
            this.number = number;
            return this;
        }

        /**
         * Sets status.
         *
         * @param status the status
         * @return the status
         */
        public PharmacyBuilder setStatus(PharmacyStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Create pharmacy pharmacy.
         *
         * @return the pharmacy
         */
        public Pharmacy createPharmacy() {
            return new Pharmacy(id, city, street, number, status);
        }
    }
}
