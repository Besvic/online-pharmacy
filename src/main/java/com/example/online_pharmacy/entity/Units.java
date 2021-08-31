package com.example.online_pharmacy.entity;

import java.util.Objects;

public class Units {

    private long id;
    private String name;

    public Units() {
    }

    public Units(long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Units units = (Units) o;
        return id == units.id && name.equals(units.name);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + name.hashCode();
    }

    @Override
    public String toString() {
        return "Units{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
