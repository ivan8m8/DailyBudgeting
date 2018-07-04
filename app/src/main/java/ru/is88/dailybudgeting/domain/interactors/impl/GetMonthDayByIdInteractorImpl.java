package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetMonthDayByIdInteractor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.repositories.MonthDayRepository;

public class GetMonthDayByIdInteractorImpl extends AbstractInteractor implements GetMonthDayByIdInteractor {

    private int monthDayId;
    private MonthDayRepository monthDayRepository;
    private GetMonthDayByIdInteractor.Callback callback;

    public GetMonthDayByIdInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                         int monthDayId,
                                         MonthDayRepository monthDayRepository,
                                         Callback callback) {
        super(threadExecutor, mainThread);
        this.monthDayId = monthDayId;
        this.monthDayRepository = monthDayRepository;
        this.callback = callback;
    }

    @Override
    public void run() {
        final MonthDay monthDay = monthDayRepository.getMonthDayById(monthDayId);
        if (monthDay == null) {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onMonthDayNotFound();
                }
            });
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onMonthDayRetrieved(monthDay);
                }
            });
        }
    }
}
