package ru.is88.dailybudgeting.domain.models.accounts;

public class Income extends AbstractAccount {

    public Income(String description, double amount, int year, int month) {
        super(description, amount, year, month);
    }
}
