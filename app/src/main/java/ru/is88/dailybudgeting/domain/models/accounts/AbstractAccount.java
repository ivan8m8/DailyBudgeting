package ru.is88.dailybudgeting.domain.models.accounts;

import java.util.Date;

public abstract class AbstractAccount {

    private long id;
    private String description;
    private double amount;
    private int year;
    private int month;

    AbstractAccount(String description, double amount, int year, int month){
        this.id = new Date().getTime();
        this.description = description;
        this.amount = amount;
        this.year = year;
        this.month = month;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
