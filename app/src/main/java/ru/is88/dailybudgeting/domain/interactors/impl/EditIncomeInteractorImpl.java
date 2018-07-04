package ru.is88.dailybudgeting.domain.interactors.impl;

import java.time.YearMonth;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditAccountInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;

public class EditIncomeInteractorImpl extends AbstractInteractor implements Interactor {

    private EditAccountInteractor.Callback callback;
    private Income income;
    private AccountRepository accountRepository;
    private String description;
    private double amount;
    private YearMonth yearMonth;

    public EditIncomeInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    EditAccountInteractor.Callback callback,
                                    Income income,
                                    AccountRepository accountRepository,
                                    String description,
                                    double amount,
                                    YearMonth yearMonth) {
        super(threadExecutor, mainThread);
        this.callback = callback;
        this.income = income;
        this.accountRepository = accountRepository;
        this.description = description;
        this.amount = amount;
        this.yearMonth = yearMonth;
    }

    @Override
    public void run() {
        //TODO
        // Check if this.income already exists in the database.
        // I am actually not sure if it's really needed to be checked,
        // but situation when might occur, I believe
    }
}
