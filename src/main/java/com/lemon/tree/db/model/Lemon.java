package com.lemon.tree.db.model;

import javax.persistence.*;

@Entity
public class Lemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String country;
    @Enumerated(EnumType.STRING)
    @Column
    private Size size;
    @Column
    private int quantity;

    public Lemon() {
    }

    public Lemon(String country, Size size, int quantity) {
        this.country = country;
        this.size = size;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
