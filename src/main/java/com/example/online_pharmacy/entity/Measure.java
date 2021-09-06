package com.example.online_pharmacy.entity;

public class Measure {

    private long id;
    private String name;

    public Measure() {
    }

    public Measure(long id, String name) {
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
        Measure measure = (Measure) o;
        return id == measure.id && name.equals(measure.name);
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
