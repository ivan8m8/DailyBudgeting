package ru.is88.dailybudgeting.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ru.is88.dailybudgeting.storage.database.MonthDaysDatabase;

@Table(database = MonthDaysDatabase.class)
public class TableMonthDay extends BaseModel {

    @PrimaryKey
    private int id;

    @Column
    private String amountString;

    @Column
    private String desc;

    @Column
    public boolean synced;

    @Column public int month;
    @Column public int year;

    public TableMonthDay() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.month = Integer.parseInt(String.valueOf(id).substring(4, 6));
        this.year = Integer.parseInt(String.valueOf(id).substring(0, 4));
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

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
