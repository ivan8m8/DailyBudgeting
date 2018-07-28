package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditAccountInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;

public class EditIncomeInteractorImpl extends AbstractInteractor implements EditAccountInteractor {

    private Income updatedIncome;

    private String description;
    private double amount;
    private int yearMonth;

    private EditAccountInteractor.Callback callback;
    private AccountRepository accountRepository;

    public EditIncomeInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    Income income,
                                    String description,
                                    double amount,
                                    int yearMonth,
                                    EditAccountInteractor.Callback callback,
                                    AccountRepository accountRepository) {
        super(threadExecutor, mainThread);

        this.callback = callback;
        this.updatedIncome = income;
        this.accountRepository = accountRepository;
        this.description = description;
        this.amount = amount;
        this.yearMonth = yearMonth;
    }

    @Override
    public void run() {
        updatedIncome.setDescription(description);
        updatedIncome.setAmount(amount);
        updatedIncome.setYearMonth(yearMonth);

        accountRepository.update(updatedIncome);

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onAccountUpdated(updatedIncome);
            }
        });
    }
}
