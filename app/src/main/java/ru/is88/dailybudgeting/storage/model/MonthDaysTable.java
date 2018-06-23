package ru.is88.dailybudgeting.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ru.is88.dailybudgeting.storage.database.MonthDaysDatabase;

@Table(database = MonthDaysDatabase.class)
public class MonthDaysTable extends BaseModel {

    @PrimaryKey
    private int id;

    @Column
    private String amountString;

    @Column
    private String descriptionString;

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

    public String getDescriptionString() {
        return descriptionString;
    }

    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
    }
}
