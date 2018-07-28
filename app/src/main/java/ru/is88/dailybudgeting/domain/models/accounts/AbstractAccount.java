package ru.is88.dailybudgeting.domain.models.accounts;

import java.util.Date;

public abstract class AbstractAccount {

    private long id;
    private String description;
    private double amount;
    private int yearMonth; // 201807 means July of 2018 year

    AbstractAccount(String description, double amount, int yearMonth){
        this.id = new Date().getTime();
        this.description = description;
        this.amount = amount;
        this.yearMonth = yearMonth;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public int getYearMonth() {
        return yearMonth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setYearMonth(int yearMonth) {
        this.yearMonth = yearMonth;
    }
}
