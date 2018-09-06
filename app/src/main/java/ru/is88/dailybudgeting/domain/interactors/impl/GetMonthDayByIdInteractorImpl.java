package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetMonthDayByIdInteractor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.Repository;

public class GetMonthDayByIdInteractorImpl extends AbstractInteractor implements GetMonthDayByIdInteractor {

    private int monthDayId;
    private Repository<MonthDay> monthDayRepository;
    private GetMonthDayByIdInteractor.Callback callback;

    public GetMonthDayByIdInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                         int monthDayId,
                                         Repository<MonthDay> monthDayRepository,
                                         Callback callback) {
        super(threadExecutor, mainThread);
        this.monthDayId = monthDayId;
        this.monthDayRepository = monthDayRepository;
        this.callback = callback;
    }

    @Override
    public void run() {
        final MonthDay monthDay = monthDayRepository.getItemById(monthDayId);
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
