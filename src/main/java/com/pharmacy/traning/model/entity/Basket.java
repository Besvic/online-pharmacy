package com.pharmacy.traning.model.entity;

import java.util.Arrays;

public class Basket {
    private long id;
    private long orderId;
    private long userId;

    public Basket() {
    }

    public Basket(long id, long orderId, long userId) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Basket basket = (Basket) o;
        return id == basket.id && orderId == basket.orderId && userId == basket.userId;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new long[] {id, orderId, userId});
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                '}';
    }

    public static class BasketBuilder{

        private long id;
        private long orderId;
        private long userId;

        public BasketBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public BasketBuilder setOrderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public BasketBuilder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Basket createBasket() {
            return new Basket(id, orderId, userId);
        }
    }
}
