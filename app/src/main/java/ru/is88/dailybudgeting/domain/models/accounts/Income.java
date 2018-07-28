package ru.is88.dailybudgeting.domain.models.accounts;

public class Income extends AbstractAccount {

    public Income(String description, double amount, int yearMonth) {
        super(description, amount, yearMonth);
    }
}
