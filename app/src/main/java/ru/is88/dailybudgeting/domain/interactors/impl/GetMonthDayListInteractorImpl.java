package ru.is88.dailybudgeting.domain.interactors.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetMonthDayListInteractor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.Repository;

public class GetMonthDayListInteractorImpl extends AbstractInteractor implements GetMonthDayListInteractor {

    private int month;
    private int year;

    private Repository<MonthDay> monthDayRepository;
    private GetMonthDayListInteractor.Callback callback;

    public GetMonthDayListInteractorImpl(Executor threadExecutor,
                                         MainThread mainThread,
                                         int month,
                                         int year,
                                         Repository<MonthDay> monthDayRepository,
                                         GetMonthDayListInteractor.Callback callback) {
        super(threadExecutor, mainThread);

        if (monthDayRepository == null || callback == null) {
            throw new IllegalArgumentException("Neither MonthDayRepository nor GetMonthDayListInteractor.Callback can be null");
        }

        this.month = month;
        this.year = year;
        this.monthDayRepository = monthDayRepository;
        this.callback = callback;
    }

    @Override
    public void run() {

        final List<MonthDay> monthDays = monthDayRepository.getItemList(month, year);

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onMonthDayListRetrieved(monthDays);
            }
        });
    }
}
