package ru.is88.dailybudgeting.presentation.presenters.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.GetMonthDayListInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.GetMonthDayListInteractorImpl;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.repositories.MonthDayRepository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter, GetMonthDayListInteractor.Callback {

    private MainPresenter.View view;
    private MonthDayRepository monthDayRepository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             MainPresenter.View view,
                             MonthDayRepository monthDayRepository) {
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
