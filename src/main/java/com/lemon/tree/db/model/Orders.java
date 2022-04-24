package com.lemon.tree.db.model;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String country;
    @Enumerated(EnumType.STRING)
    @Column
    private Size size;
    @Column
    private int quantity;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    public Orders() {
    }

    public Orders(String country, Size size, int quantity) {
        this.country = country;
        this.size = size;
        this.quantity = quantity;
        this.orderDate = new Date();
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
