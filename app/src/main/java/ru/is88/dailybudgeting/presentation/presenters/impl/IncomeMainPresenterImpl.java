package ru.is88.dailybudgeting.presentation.presenters.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.GetItemListInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.GetItemListInteractorImpl;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;

public class IncomeMainPresenterImpl extends AbstractPresenter
        implements MainPresenter, GetItemListInteractor.Callback<Income> {

    private Repository<Income> mRepository;
    private MainPresenter.View<Income> mView;

    public IncomeMainPresenterImpl(Executor executor,
                                   MainThread mainThread,
                                   Repository<Income> repository,
                                   MainPresenter.View<Income> view) {
        super(executor, mainThread);
        mRepository = repository;
        mView = view;
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
    public void onItemListRetrieved(List<Income> list) {
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
}
