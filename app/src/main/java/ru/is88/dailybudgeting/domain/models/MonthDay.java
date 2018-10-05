package ru.is88.dailybudgeting.domain.models;

/**
 * It's one of those card views.
 */

public class MonthDay {

    /**
     * Id is three values that are: year, month and day
     * example: 20170803
     */
    private int mId;

    private Cell mAmountCell;

    private String mDescription;

    private int day;
    private int month;
    private int year;

    public MonthDay(int id,
                    Cell amountCell,
                    String description){
        mId = id;
        mAmountCell = amountCell;
        mDescription = description;

        this.year = Integer.parseInt(String.valueOf(id).substring(0, 4));
        this.month = Integer.parseInt(String.valueOf(id).substring(4, 6));
        this.day = Integer.parseInt(String.valueOf(id).substring(6, 8));
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Cell getAmountCell() {
        return mAmountCell;
    }

    public void setAmountCell(Cell amountCell) {
        mAmountCell = amountCell;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
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
