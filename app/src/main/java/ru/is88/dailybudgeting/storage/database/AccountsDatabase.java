package ru.is88.dailybudgeting.storage.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AccountsDatabase.NAME, version = AccountsDatabase.VERSION)
public class AccountsDatabase {
    public static final String NAME = "Accounts_DB";
    public static final int VERSION = 1;
}
