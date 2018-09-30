package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.AddItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public class AddIncomeInteractorImpl extends AbstractInteractor implements AddItemInteractor {

    private int mYear;
    private int mMonth;
    private double mAmount;
    private String mDescription;

    private Repository<Income> mRepository;
    private AddItemInteractor.Callback mCallback;

    public AddIncomeInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                   int year,
                                   int month,
                                   double amount,
                                   String description,
                                   Repository<Income> incomeRepository,
                                   Callback callback) {
        super(threadExecutor, mainThread);

        mYear = year;
        mMonth = month;
        mAmount = amount;
        mDescription = description;
        mRepository = incomeRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        final Income income = new Income(mYear, mMonth, mAmount, mDescription);
        mRepository.insert(income);
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onItemAdded();
            }
        });
    }
}
