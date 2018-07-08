package ru.is88.dailybudgeting.domain.models;

import android.util.Log;

/**
 * It's one of those card views.
 */

public class MonthDay {

    /**
     * Id is three numbers consists day, month and year
     * example: 20170803
     */
    private int id;

    /**
     * This is String, because it's easier to user to sum up all his expenses.
     * Moreover, it's more like an Excel cell.
     */
    private String amountString;

    private String description;

    private int day;
    private int month;
    private int year;

    public MonthDay(int id,
                    String amountString,
                    String description){
        this.id = id;
        this.amountString = amountString;
        this.description = description;

        this.day = Integer.parseInt(String.valueOf(id).substring(6, 8));
        this.month = Integer.parseInt(String.valueOf(id).substring(4, 6));
        this.year = Integer.parseInt(String.valueOf(id).substring(0, 4));
    }

    public double getAmount() {
        double result = 0;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
