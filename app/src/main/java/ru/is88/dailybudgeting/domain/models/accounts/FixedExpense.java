package ru.is88.dailybudgeting.domain.models.accounts;

import ru.is88.dailybudgeting.domain.models.Cell;

public class FixedExpense extends AbstractAccount {

    public FixedExpense(int year, int month, Cell amountCell, String description) {
        super(year, month, amountCell, description);
    }

    public FixedExpense(long id, int year, int month, Cell amountCell, String description) {
        super(id, year, month, amountCell, description);
    }
}
