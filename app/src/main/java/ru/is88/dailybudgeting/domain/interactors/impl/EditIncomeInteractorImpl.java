package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public class EditIncomeInteractorImpl extends AbstractInteractor implements EditItemInteractor {

    private Income mUpdatedIncome;

    private long mId;
    private Cell mAmountCell;
    private String mDescription;

    private Repository<Income> mIncomeRepository;
    private EditItemInteractor.Callback<Income> mCallback;

    public EditIncomeInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                    long id,
                                    Cell amountCell,
                                    String description,
                                    Repository<Income> incomeRepository,
                                    EditItemInteractor.Callback<Income> callback) {
        super(threadExecutor, mainThread);

        mId = id;
        mAmountCell = amountCell;
        mDescription = description;
        mIncomeRepository = incomeRepository;
        mCallback = callback;
    }

    @Override
    public void run() {

        mUpdatedIncome = mIncomeRepository.getItemById(mId);

        mUpdatedIncome.setAmountCell(mAmountCell);
        mUpdatedIncome.setDescription(mDescription);

        mIncomeRepository.update(mUpdatedIncome);

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onItemUpdated(mUpdatedIncome);
            }
        });
    }
}
