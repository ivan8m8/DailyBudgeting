package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.EditItemInteractor;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;

public class EditFixedExpenseInteractorImpl extends AbstractInteractor implements EditItemInteractor {

    private FixedExpense mUpdatedFixedExpense;

    private double mAmount;
    private String mDescription;

    private Repository<FixedExpense> mRepository;
    private EditItemInteractor.Callback<FixedExpense> mCallback;

    public EditFixedExpenseInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                          FixedExpense updatedFixedExpense,
                                          double amount,
                                          String description,
                                          Repository<FixedExpense> fixedExpenseRepository,
                                          Callback<FixedExpense> callback) {
        super(threadExecutor, mainThread);
        mUpdatedFixedExpense = updatedFixedExpense;
        mAmount = amount;
        mDescription = description;
        mRepository = fixedExpenseRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        mUpdatedFixedExpense.setAmount(mAmount);
        mUpdatedFixedExpense.setDescription(mDescription);

        mRepository.update(mUpdatedFixedExpense);

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onItemUpdated(mUpdatedFixedExpense);
            }
        });
    }
}
