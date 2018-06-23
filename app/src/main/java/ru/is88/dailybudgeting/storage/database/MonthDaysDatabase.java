package ru.is88.dailybudgeting.storage.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MonthDaysDatabase.NAME, version = MonthDaysDatabase.VERSION)
public class MonthDaysDatabase {
    public static final String NAME = "MonthDays_DB";
    public static final int VERSION = 1;
}
