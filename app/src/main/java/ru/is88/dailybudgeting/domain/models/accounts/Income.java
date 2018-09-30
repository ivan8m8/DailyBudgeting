package ru.is88.dailybudgeting.domain.models.accounts;

public class Income extends AbstractAccount {

    public Income(int year, int month, double amount, String description) {
        super(year, month, amount, description);
    }

    public Income(long id, int year, int month, double amount, String description) {
        super(id, year, month, amount, description);
    }
}
