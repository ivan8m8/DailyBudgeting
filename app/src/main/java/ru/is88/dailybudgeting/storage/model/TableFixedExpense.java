package ru.is88.dailybudgeting.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.storage.database.DailyBudgetingDatabase;

@Table(database = DailyBudgetingDatabase.class)
public class TableFixedExpense extends BaseModel {

    @PrimaryKey
    private long id;

    @Column
    private Cell amountCell;

    @Column
    private String description;

    @Column
    private int year;

    @Column
    private int month;

    @Column
    public boolean synced;

    public TableFixedExpense() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cell getAmountCell() {
        return amountCell;
    }

    public void setAmountCell(Cell amountCell) {
        this.amountCell = amountCell;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
