package ru.is88.dailybudgeting.domain.models.accounts;

public class FixedExpense extends AbstractAccount {

    public FixedExpense(String description, double amount, int year, int month) {
        super(description, amount, year, month);
    }
}
