package ru.is88.dailybudgeting.domain.repositories;

import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public interface AccountRepository {

    void insert(Income income);
    void update(Income income);

    void insert(FixedExpense fixedExpense);
    void update(FixedExpense fixedExpense);

    AbstractAccount getAccountById(long id);
}
