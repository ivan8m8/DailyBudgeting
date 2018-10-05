package ru.is88.dailybudgeting.presentation.presenters.impl;

import android.support.annotation.NonNull;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetItemByIdInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.EditMonthDayInteractorImpl;
import ru.is88.dailybudgeting.domain.interactors.impl.GetItemByIdInteractorImpl;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditItemPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditMonthDayPresenter;

public class EditMonthDayPresenterImpl extends AbstractPresenter
        implements EditMonthDayPresenter, GetItemByIdInteractor.Callback<MonthDay>, EditItemInteractor.Callback<MonthDay> {

    private Repository<MonthDay> mRepository;
    private EditItemPresenter.View<MonthDay> mView;

    public EditMonthDayPresenterImpl(Executor executor, MainThread mainThread,
                                     Repository<MonthDay> repository,
                                     EditItemPresenter.View<MonthDay> view) {
        super(executor, mainThread);
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getMonthDayById(int monthDayId) {
        GetItemByIdInteractor getMonthDayByIdInteractor =
                new GetItemByIdInteractorImpl<>(
                        mExecutor,
                        mMainThread,
                        monthDayId,
                        mRepository,
                        this
                );
        getMonthDayByIdInteractor.execute();
    }

    @Override
    public void onItemRetrieved(MonthDay item) {
        mView.onItemRetrieved(item);
    }

    @Override
    public void onItemNotFound() {
        mView.showError("No month day found");
    }

    @Override
    public void editMonthDay(int monthDayId, Cell amountCell, String description) {
        EditItemInteractor editItemInteractor =
                new EditMonthDayInteractorImpl(
                        mExecutor,
                        mMainThread,
                        monthDayId,
                        amountCell,
                        description,
                        mRepository,
                        this
                );
        editItemInteractor.execute();
    }

    @Override
    public void onItemUpdated(@NonNull MonthDay item) {
        mView.onItemUpdated(item);
    }
}
