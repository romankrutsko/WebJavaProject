package com.example.workshop.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;

    private String name;

    private String surname;

    private double account;

    private String role;

    public User() {

    }

    public User(int id, String name, String surname, double account) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.account = account;
    }

    public User(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
