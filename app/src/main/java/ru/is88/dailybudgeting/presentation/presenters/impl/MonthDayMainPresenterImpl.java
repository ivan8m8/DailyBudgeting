package ru.is88.dailybudgeting.presentation.presenters.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.GetMonthDayListInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.GetMonthDayListInteractorImpl;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.MonthDayMainPresenter;

public class MonthDayMainPresenterImpl extends AbstractPresenter implements MonthDayMainPresenter, GetMonthDayListInteractor.Callback {

    private MonthDayMainPresenter.View view;
    private Repository<MonthDay> monthDayRepository;

    public MonthDayMainPresenterImpl(Executor executor,
                                     MainThread mainThread,
                                     MonthDayMainPresenter.View view,
                                     Repository<MonthDay> monthDayRepository) {
        super(executor, mainThread);

        this.view = view;
        this.monthDayRepository = monthDayRepository;
    }

    @Override
    public void getMonthDayList(int month, int year) {

        GetMonthDayListInteractor getMonthDayListInteractor =
                new GetMonthDayListInteractorImpl(
                        this.executor,
                        this.mainThread,
                        month,
                        year,
                        monthDayRepository,
                        this
                );
        getMonthDayListInteractor.execute();
    }

    @Override
    public void onMonthDayListRetrieved(List<MonthDay> monthDays) {
        view.showMonthDays(monthDays);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
