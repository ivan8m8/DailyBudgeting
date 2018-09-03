package ru.is88.dailybudgeting.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ru.is88.dailybudgeting.storage.database.DailyBudgetingDatabase;

@Table(database = DailyBudgetingDatabase.class)
public class TableMonthDay extends BaseModel {

    @PrimaryKey
    private int id;

    @Column
    private String amountString;

    @Column
    private String desc;

    @Column
    public boolean synced;

    @Column public int day;
    @Column public int month;
    @Column public int year;

    public TableMonthDay() {}

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
