package ru.is88.dailybudgeting.domain.interactors.base.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditMonthDayInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.repositories.MonthDayRepository;

public class EditMonthDayInteractorImpl extends AbstractInteractor implements Interactor {

    private EditMonthDayInteractor.Callback callback;
    private MonthDay monthDay;
    private MonthDayRepository monthDayRepository;

    public EditMonthDayInteractorImpl(Executor threadExecutor,
                                      MainThread mainThread,
                                      EditMonthDayInteractor.Callback callback,
                                      MonthDay monthDay,
                                      MonthDayRepository monthDayRepository) {
        super(threadExecutor, mainThread);

        this.callback = callback;
        this.monthDay = monthDay;
        this.monthDayRepository = monthDayRepository;
    }

    @Override
    public void run() {
        // check if it exists in database

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onMonthDayUpdated(monthDay);
            }
        });
    }
}
