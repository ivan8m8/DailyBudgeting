package ru.is88.dailybudgeting.domain.models.accounts;

import java.time.YearMonth;
import java.util.Date;

public abstract class AbstractAccount {

    private long id;
    private String description;
    private double amount;
    private YearMonth yearMonth;

    AbstractAccount(String description, double amount, YearMonth yearMonth){
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

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }
}
