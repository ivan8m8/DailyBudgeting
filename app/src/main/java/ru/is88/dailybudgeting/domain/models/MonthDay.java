package ru.is88.dailybudgeting.domain.models;

public class MonthDay {

    /**
     * Id is three numbers consists day, month and year
     * example: 31122017
     */
    private int id;

    /**
     * This is String, because it's easier to user to sum up all his expenses.
     * Moreover, it's more like an Excel cell.
     */
    private String amountString;
    private String description;

    public MonthDay(int id,
                    String amountString,
                    String description){
        this.id = id;
        this.amountString = amountString;
        this.description = description;
    }

    public int getId() {
        return id;
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
}
