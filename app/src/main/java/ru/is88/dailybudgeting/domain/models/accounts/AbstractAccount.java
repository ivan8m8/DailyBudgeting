package ru.is88.dailybudgeting.domain.models.accounts;

import java.util.Date;

public abstract class AbstractAccount {

    private long mId;
    private int mYear;
    private int mMonth;
    private double mAmount;
    private String mDescription;

    AbstractAccount(int year, int month, double amount, String description){
        mId = new Date().getTime();
        mYear = year;
        mMonth = month;
        mAmount = amount;
        mDescription = description;
    }

    /*
    This constructor is for an already existing account that should be converted to domain model.
     */
    AbstractAccount(long id, int year, int month, double amount, String description) {
        mId = id;
        mDescription = description;
        mAmount = amount;
        mYear = year;
        mMonth = month;
    }

    public long getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        mMonth = month;
    }
}
