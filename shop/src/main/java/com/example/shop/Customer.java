package com.example.shop;

public class Customer {
    private final int id;
    private double balance;

    public Customer(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void charge(double value) {
        this.balance -= value;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }
}
