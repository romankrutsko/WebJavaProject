package com.example.workshop.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Order {
    private int id;

    private int users_id;

    private String status;

    private java.sql.Date date;

    private String master;

    private double price;

    public Order() {}

    public Order(int id, int users_id, String status, Date date, String master, double price) {
        this.id = id;
        this.users_id = users_id;
        this.status = status;
        this.date = date;
        this.master = master;
        this.price = price;
    }

}
