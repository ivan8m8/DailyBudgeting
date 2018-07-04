package ru.is88.dailybudgeting.domain.interactors.impl;


import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditMonthDayInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.repositories.MonthDayRepository;

public class EditMonthDayInteractorImpl extends AbstractInteractor implements EditMonthDayInteractor {

    private EditMonthDayInteractor.Callback callback;
    private MonthDayRepository monthDayRepository;

    private MonthDay mUpdatedMonthDay;

    private int mId;
    private String mAmountString;
    private String mDescription;

    public EditMonthDayInteractorImpl(Executor threadExecutor,
                                      MainThread mainThread,
                                      EditMonthDayInteractor.Callback callback,
                                      MonthDayRepository monthDayRepository,
                                      int id,
                                      String amountString,
                                      String description) {
        super(threadExecutor, mainThread);

        this.callback = callback;
        this.monthDayRepository = monthDayRepository;
        this.mId = id;
        this.mAmountString = amountString;
        this.mDescription = description;
    }

    @Override
    public void run() {
        // check if it exists in the database
        mUpdatedMonthDay = monthDayRepository.getMonthDayById(mId);
        if (mUpdatedMonthDay == null){
            mUpdatedMonthDay = new MonthDay(mId, mAmountString, mDescription);
            monthDayRepository.insert(mUpdatedMonthDay);
        } else {
            mUpdatedMonthDay.setAmountString(mAmountString);
            mUpdatedMonthDay.setDescription(mDescription);
            monthDayRepository.update(mUpdatedMonthDay);
        }

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onMonthDayUpdated(mUpdatedMonthDay);
            }
        });
    }
}
