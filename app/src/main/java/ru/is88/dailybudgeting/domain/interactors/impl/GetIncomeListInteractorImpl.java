package ru.is88.dailybudgeting.domain.interactors.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetAccountListInteractor;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;

public class GetIncomeListInteractorImpl extends AbstractInteractor implements GetAccountListInteractor {

    private int month;
    private int year;

    private AccountRepository accountRepository;
    private GetAccountListInteractor.Callback callback;

    public GetIncomeListInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                       int month,
                                       int year,
                                       AccountRepository accountRepository,
                                       Callback callback) {
        super(threadExecutor, mainThread);

        this.month = month;
        this.year = year;
        this.accountRepository = accountRepository;
        this.callback = callback;
    }

    @Override
    public void run() {
        final List<Income> incomes = accountRepository.getIncomeList(month, year);
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onAccountListRetrieved(incomes);
            }
        });
    }
}
