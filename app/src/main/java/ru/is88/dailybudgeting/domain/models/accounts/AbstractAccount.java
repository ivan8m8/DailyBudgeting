package ru.is88.dailybudgeting.domain.models.accounts;

import java.util.Date;

import ru.is88.dailybudgeting.domain.models.Cell;

public abstract class AbstractAccount {

    private long mId;
    private int mYear;
    private int mMonth;
    private Cell mAmountCell;
    private String mDescription;

    AbstractAccount(int year, int month, Cell amountCell, String description) {
        mId = new Date().getTime();
        mYear = year;
        mMonth = month;
        mAmountCell = amountCell;
        mDescription = description;
    }

    /*
    This constructor is for an already existing account that should be converted to domain model.
     */
    AbstractAccount(long id, int year, int month, Cell amountCell, String description) {
        mId = id;
        mDescription = description;
        mAmountCell = amountCell;
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

    public Cell getAmountCell() {
        return mAmountCell;
    }

    public void setAmountCell(Cell amountCell) {
        mAmountCell = amountCell;
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
