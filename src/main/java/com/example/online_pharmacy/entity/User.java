package com.example.online_pharmacy.entity;

import java.util.Arrays;

public class User {

    private int id;
    private Position position;
    private Status status;
    private String name;
    private double cash;
    private String login;
    private String password;

    public User() {
    }

    public User(int id, String name, double cash, String login, String password, Status status, Position position) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        this.login = login;
        this.password = password;
        this.status = status;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id && cash == user.cash && status == user.status && position.equals(user.position) 
                && name.equals(user.name) && login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() { // TODO: 23.08.2021  add position and status 
        return Arrays.hashCode(new int[]{id, (int) cash}) + login.hashCode() + password.hashCode() + name.hashCode();
    }

    @Override // FIXME: 23.08.2021  to string builder
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cash=" + cash +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", idStatus=" + idStatus +
                '}';
    }
}
