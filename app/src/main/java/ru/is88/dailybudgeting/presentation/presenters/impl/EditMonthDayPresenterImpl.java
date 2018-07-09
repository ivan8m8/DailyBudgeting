package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditMonthDayInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetMonthDayByIdInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.GetMonthDayByIdInteractorImpl;
import ru.is88.dailybudgeting.domain.interactors.impl.EditMonthDayInteractorImpl;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.repositories.MonthDayRepository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditMonthDayPresenter;

public class EditMonthDayPresenterImpl extends AbstractPresenter
        implements EditMonthDayPresenter, GetMonthDayByIdInteractor.Callback, EditMonthDayInteractor.Callback {

    private EditMonthDayPresenter.View view;
    private MonthDayRepository monthDayRepository;

    public EditMonthDayPresenterImpl(Executor executor, MainThread mainThread,
                                     View view, MonthDayRepository monthDayRepository) {
        super(executor, mainThread);
        this.view = view;
        this.monthDayRepository = monthDayRepository;
    }

    @Override
    public void getMonthDayById(int monthDayId) {
        GetMonthDayByIdInteractor getMonthDayByIdInteractor =
                new GetMonthDayByIdInteractorImpl(
                        executor,
                        mainThread,
                        monthDayId,
                        monthDayRepository,
                        this
                );
        getMonthDayByIdInteractor.execute();
    }

    @Override
    public void onMonthDayRetrieved(MonthDay monthDay) {
        view.onMonthDayRetrieved(monthDay);
    }

    @Override
    public void onMonthDayNotFound() {
        view.showError("No month day found");
    }

    @Override
    public void editMonthDay(int monthDayId, String description, String amount) {
        EditMonthDayInteractor editMonthDayInteractor =
                new EditMonthDayInteractorImpl(
                        executor,
                        mainThread,
                        this,
                        monthDayRepository,
                        monthDayId, amount, description
                );
        editMonthDayInteractor.execute();
    }

    @Override
    public void onMonthDayUpdated(final MonthDay monthDay) {
        view.onMonthDayUpdated(monthDay);
    }

}
