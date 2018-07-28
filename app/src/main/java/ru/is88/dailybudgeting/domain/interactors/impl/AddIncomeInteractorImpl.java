package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.AddAccountInteractor;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;

public class AddIncomeInteractorImpl extends AbstractInteractor implements AddAccountInteractor {

    private String description;
    private double amount;
    private int yearMonth;

    private AccountRepository accountRepository;
    private AddAccountInteractor.Callback callback;

    public AddIncomeInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                   String description,
                                   double amount,
                                   int yearMonth,
                                   AccountRepository accountRepository,
                                   Callback callback) {
        super(threadExecutor, mainThread);

        this.description = description;
        this.amount = amount;
        this.yearMonth = yearMonth;
        this.accountRepository = accountRepository;
        this.callback = callback;
    }

    @Override
    public void run() {
        final Income income = new Income(description, amount, yearMonth);
        accountRepository.insert(income);
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onAccountAdded(income);
            }
        });
    }
}
