package com.example.online_pharmacy.entity;

import java.time.LocalDate;
import java.util.Arrays;

public class Order {

    private long id;
    private long productId;
    private long userId;
    private OrderStatus status;
    private int quantity;
    private LocalDate date;

    public Order() {
    }

    public Order(long id, long productId, long userId, OrderStatus status, int quantity, LocalDate date) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.status = status;
        this.quantity = quantity;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return id == order.id && productId == order.productId && userId == order.userId
                && quantity == order.quantity && status == order.status && date.equals(order.date);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new long[] {id, productId, userId}) + Integer.hashCode(quantity) + status.hashCode()
                + date.hashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userId +
                ", status=" + status.toString() +
                ", quantity=" + quantity +
                ", date=" + date.toString() +
                '}';
    }

    public static class OrderBuilder{

        private long id;
        private long productId;
        private long userId;
        private OrderStatus status;
        private int quantity;
        private LocalDate date;

        public OrderBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setProductId(long productId) {
            this.productId = productId;
            return this;
        }

        public OrderBuilder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public OrderBuilder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Order createOrder() {
            return new Order(id, productId, userId, status, quantity, date);
        }
    }

}
