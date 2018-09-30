package ru.is88.dailybudgeting.presentation.presenters.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.GetItemListInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.GetItemListInteractorImpl;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;

public class MonthDayMainPresenterImpl extends AbstractPresenter
        implements MainPresenter, GetItemListInteractor.Callback<MonthDay> {

    private Repository<MonthDay> mRepository;
    private MainPresenter.View<MonthDay> mView;

    public MonthDayMainPresenterImpl(Executor executor,
                                     MainThread mainThread,
                                     Repository<MonthDay> repository,
                                     MainPresenter.View<MonthDay> view) {
        super(executor, mainThread);
        this.mRepository = repository;
        this.mView = view;
    }

    @Override
    public void getItemList(int year, int month) {
        GetItemListInteractor getItemListInteractor =
                new GetItemListInteractorImpl<>(
                        mExecutor,
                        mMainThread,
                        year,
                        month,
                        mRepository,
                        this
                );
        getItemListInteractor.execute();
    }

    @Override
    public void onItemListRetrieved(List<MonthDay> list) {
        mView.showItemList(list);
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

//    public MonthDayMainPresenterImpl(Executor executor,
//                                     MainThread mainThread,
//                                     MonthDayMainPresenter.View mView,
//                                     Repository<MonthDay> monthDayRepository) {
//        super(executor, mainThread);
//
//        this.mView = mView;
//        this.monthDayRepository = monthDayRepository;
//    }

//    @Override
//    public void getMonthDayList(int month, int year) {
//
//        GetMonthDayListInteractor getMonthDayListInteractor =
//                new GetMonthDayListInteractorImpl(
//                        this.mExecutor,
//                        this.mMainThread,
//                        month,
//                        year,
//                        monthDayRepository,
//                        this
//                );
//        getMonthDayListInteractor.execute();
//    }
//
//    @Override
//    public void onMonthDayListRetrieved(List<MonthDay> monthDays) {
//        mView.showMonthDays(monthDays);
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void stop() {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void onError(String message) {
//
//    }
}
