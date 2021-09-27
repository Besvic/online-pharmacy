package com.pharmacy.traning.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Order {

    private long id;
    private Product product;
    private User user;
    private Pharmacy pharmacy;
    private OrderStatus status;
    private LocalDate date;
    private int quantity;

    public Order() {
    }

    public Order(long id, Product product, User user, Pharmacy pharmacy, OrderStatus status, LocalDate date, int quantity) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.pharmacy = pharmacy;
        this.status = status;
        this.date = date;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static class OrderBuilder{


        private long id;
        private Product product;
        private User user;
        private Pharmacy pharmacy;
        private OrderStatus status;
        private LocalDate date;
        private int quantity;

        public OrderBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public OrderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder setPharmacy(Pharmacy pharmacy) {
            this.pharmacy = pharmacy;
            return this;
        }

        public OrderBuilder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public OrderBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Order createOrder() {
            return new Order(id, product, user, pharmacy, status, date, quantity);
        }
    }

}
