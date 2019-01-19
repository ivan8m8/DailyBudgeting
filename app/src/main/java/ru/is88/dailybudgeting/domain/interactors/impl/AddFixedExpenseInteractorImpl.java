package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.AddItemInteractor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;

public class AddFixedExpenseInteractorImpl extends AbstractInteractor implements AddItemInteractor {

    private int mYear;
    private int mMonth;
    private Cell mAmountCell;
    private String mDescription;

    private Repository<FixedExpense> mRepository;
    private AddItemInteractor.Callback<FixedExpense> mCallback;

    public AddFixedExpenseInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                         int year,
                                         int month,
                                         Cell amountCell,
                                         String description,
                                         Repository<FixedExpense> repository,
                                         AddItemInteractor.Callback<FixedExpense> callback) {
        super(threadExecutor, mainThread);
        mYear = year;
        mMonth = month;
        mAmountCell = amountCell;
        mDescription = description;
        mRepository = repository;
        mCallback = callback;
    }

    @Override
    public void run() {
        final FixedExpense fixedExpense = new FixedExpense(mYear, mMonth, mAmountCell, mDescription);
        mRepository.insert(fixedExpense);
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onItemAdded(fixedExpense);
            }
        });
    }
}
