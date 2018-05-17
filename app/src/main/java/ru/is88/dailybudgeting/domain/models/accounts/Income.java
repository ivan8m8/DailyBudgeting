package ru.is88.dailybudgeting.domain.models.accounts;

import java.time.YearMonth;

public class Income extends Account {

    Income(String description, double amount, YearMonth yearMonth) {
        super(description, amount, yearMonth);
    }
}
