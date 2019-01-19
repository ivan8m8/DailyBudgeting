package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.AddItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public class AddIncomeInteractorImpl extends AbstractInteractor implements AddItemInteractor {

    private int mYear;
    private int mMonth;
    private Cell mAmountCell;
    private String mDescription;

    private Repository<Income> mRepository;
    private AddItemInteractor.Callback<Income> mCallback;

    public AddIncomeInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                   int year,
                                   int month,
                                   Cell amountCell,
                                   String description,
                                   Repository<Income> incomeRepository,
                                   AddItemInteractor.Callback<Income> callback) {
        super(threadExecutor, mainThread);

        mYear = year;
        mMonth = month;
        mAmountCell = amountCell;
        mDescription = description;
        mRepository = incomeRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        final Income income = new Income(mYear, mMonth, mAmountCell, mDescription);
        mRepository.insert(income);
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onItemAdded(income);
            }
        });
    }
}
