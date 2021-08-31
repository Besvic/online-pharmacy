package com.example.online_pharmacy.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Order {

    private long id;
    private long userID;
    private OrderStatus status;
    private int quantity;
    private LocalDate date;

    public Order() {
    }

    public Order(long id, long userID, OrderStatus status, int quantity, LocalDate date) {
        this.id = id;
        this.userID = userID;
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

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
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
        return id == order.id && userID == order.userID && quantity == order.quantity && status == order.status && date.equals(order.date);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new long[] {id, userID}) + Integer.hashCode(quantity) + status.hashCode() + date.hashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", status=" + status.toString() +
                ", quantity=" + quantity +
                ", date=" + date.toString() +
                '}';
    }
}
