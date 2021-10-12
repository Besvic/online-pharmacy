package com.pharmacy.traning.model.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Order.
 */
public class Order {

    private long id;
    private Product product;
    private User user;
    private Pharmacy pharmacy;
    private OrderStatus status;
    private LocalDate date;
    private int quantity;

    /**
     * Instantiates a new Order.
     *
     * @param id       the id
     * @param product  the product
     * @param user     the user
     * @param pharmacy the pharmacy
     * @param status   the status
     * @param date     the date
     * @param quantity the quantity
     */
    public Order(long id, Product product, User user, Pharmacy pharmacy, OrderStatus status, LocalDate date, int quantity) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.pharmacy = pharmacy;
        this.status = status;
        this.date = date;
        this.quantity = quantity;
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
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets pharmacy.
     *
     * @return the pharmacy
     */
    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    /**
     * Sets pharmacy.
     *
     * @param pharmacy the pharmacy
     */
    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
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

    /***
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
        Order order = (Order) o;
        return id == order.id && quantity == order.quantity && product == null ? order.product == null : product.equals(order.product) &&
                user == null ? order.user == null : user.equals(order.user) &&
                pharmacy == null ? order.pharmacy == null : pharmacy.equals(order.pharmacy) &&
                status == null ? order.status == null : status.equals(order.status) &&
                date == null ? order.date == null : date.equals(order.date);
    }

    /***
     * Hash Code
     * @return
     */
    @Override
    public int hashCode() {
        return Long.hashCode(id) + product.hashCode() + user.hashCode() + pharmacy.hashCode() +
                status.hashCode() + date.hashCode() +  Integer.hashCode(quantity);
    }

    /***
     * To String
     * @return
     */
    @Override
    public String toString() {
        return new String(new StringBuilder()
                .append("Order{")
                .append("id=").append(id)
                .append(", product=").append(product.toString())
                .append(", user=").append(user.toString())
                .append(", pharmacy=").append(pharmacy.toString())
                .append(", status=").append(status)
                .append(", date=").append(date)
                .append(", quantity=").append(quantity)
                .append('}'));
    }

    /**
     * The type Order builder.
     */
    public static class OrderBuilder{

        private long id;
        private Product product;
        private User user;
        private Pharmacy pharmacy;
        private OrderStatus status;
        private LocalDate date;
        private int quantity;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public OrderBuilder setId(long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets product.
         *
         * @param product the product
         * @return the product
         */
        public OrderBuilder setProduct(Product product) {
            this.product = product;
            return this;
        }

        /**
         * Sets user.
         *
         * @param user the user
         * @return the user
         */
        public OrderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        /**
         * Sets pharmacy.
         *
         * @param pharmacy the pharmacy
         * @return the pharmacy
         */
        public OrderBuilder setPharmacy(Pharmacy pharmacy) {
            this.pharmacy = pharmacy;
            return this;
        }

        /**
         * Sets status.
         *
         * @param status the status
         * @return the status
         */
        public OrderBuilder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Sets date.
         *
         * @param date the date
         * @return the date
         */
        public OrderBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        /**
         * Sets quantity.
         *
         * @param quantity the quantity
         * @return the quantity
         */
        public OrderBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Create order order.
         *
         * @return the order
         */
        public Order createOrder() {
            return new Order(id, product, user, pharmacy, status, date, quantity);
        }
    }

}
