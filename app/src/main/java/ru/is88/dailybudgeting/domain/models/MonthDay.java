package ru.is88.dailybudgeting.domain.models;

public class MonthDay {

    /**
     * id is three numbers consists day, month and year
     * example: 31122017
     */
    private int id;

    private double amount;
    private String description;

    public MonthDay(double amount,
                    String description){
        //this.id = need to obtain smth like 05-2018
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
