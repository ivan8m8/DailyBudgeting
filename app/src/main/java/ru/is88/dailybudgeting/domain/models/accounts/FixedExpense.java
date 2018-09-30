package ru.is88.dailybudgeting.domain.models.accounts;

public class FixedExpense extends AbstractAccount {

    public FixedExpense(int year, int month, double amount, String description) {
        super(year, month, amount, description);
    }

    public FixedExpense(long id, int year, int month, double amount, String description) {
        super(id, year, month, amount, description);
    }
}
