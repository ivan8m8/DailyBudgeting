package ru.is88.dailybudgeting.domain.models.accounts;

import java.time.YearMonth;

public class FixedExpense extends Account {

    FixedExpense(String description, double amount, YearMonth yearMonth) {
        super(description, amount, yearMonth);
    }
}
