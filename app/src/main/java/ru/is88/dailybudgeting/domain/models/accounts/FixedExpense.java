package ru.is88.dailybudgeting.domain.models.accounts;

public class FixedExpense extends AbstractAccount {

    public FixedExpense(String description, double amount, int yearMonth) {
        super(description, amount, yearMonth);
    }
}
