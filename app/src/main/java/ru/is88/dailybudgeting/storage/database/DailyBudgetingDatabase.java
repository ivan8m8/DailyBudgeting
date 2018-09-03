package ru.is88.dailybudgeting.storage.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = DailyBudgetingDatabase.NAME, version = DailyBudgetingDatabase.VERSION)
public class DailyBudgetingDatabase {
    public static final String NAME = "DailyBudgeting_DB";
    public static final int VERSION = 1;
}
