package ru.is88.dailybudgeting.domain.repositories;

import java.util.List;

import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public interface AccountRepository {

    /*
    Unable define the abstract methods within this interface,
    since within its implementation I have to retrieve accounts from a specific table.
     */

    void insert(Income income);
    void update(Income income);
    Income getIncomeById(long id);
    List<Income> getIncomeList(int month, int year);

    void insert(FixedExpense fixedExpense);
    void update(FixedExpense fixedExpense);
    FixedExpense getFixedExpenseById(long id);
    List<FixedExpense> getFixedExpenseList(int month, int year);
}
