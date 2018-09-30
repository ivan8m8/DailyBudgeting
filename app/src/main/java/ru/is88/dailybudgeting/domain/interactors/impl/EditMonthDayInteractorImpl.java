package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.Repository;

public class EditMonthDayInteractorImpl extends AbstractInteractor implements EditItemInteractor {

    private int mId;
    private String mAmountString;
    private String mDescription;

    private Repository<MonthDay> mMonthDayRepository;
    private EditItemInteractor.Callback<MonthDay> mCallback;

    private MonthDay mUpdatedMonthDay;

    public EditMonthDayInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                      int id,
                                      String amountString,
                                      String description,
                                      Repository<MonthDay> monthDayRepository,
                                      EditItemInteractor.Callback<MonthDay> callback) {
        super(threadExecutor, mainThread);

        mId = id;
        mAmountString = amountString;
        mDescription = description;
        mMonthDayRepository = monthDayRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        // check if it exists in the database
        mUpdatedMonthDay = mMonthDayRepository.getItemById(mId);
        if (mUpdatedMonthDay == null){
            mUpdatedMonthDay = new MonthDay(mId, mAmountString, mDescription);
            mMonthDayRepository.insert(mUpdatedMonthDay);
        } else {
            mUpdatedMonthDay.setAmountString(mAmountString);
            mUpdatedMonthDay.setDescription(mDescription);
            mMonthDayRepository.update(mUpdatedMonthDay);
        }

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onItemUpdated(mUpdatedMonthDay);
            }
        });
    }
}
