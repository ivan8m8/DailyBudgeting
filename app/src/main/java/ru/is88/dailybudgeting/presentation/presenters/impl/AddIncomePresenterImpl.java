package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.AddItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.AddIncomeInteractorImpl;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AddAccountPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AddItemPresenter;

public class AddIncomePresenterImpl extends AbstractPresenter
        implements AddItemPresenter, AddAccountPresenter, AddItemInteractor.Callback {

    private Repository<Income> mRepository;
    private AddItemPresenter.View mView;

    public AddIncomePresenterImpl(Executor executor, MainThread mainThread,
                                  Repository<Income> repository,
                                  AddItemPresenter.View view) {
        super(executor, mainThread);
        mRepository = repository;
        mView = view;
    }

    @Override
    public void addNewAccount(int year, int month, Cell amountCell, String description) {
        AddItemInteractor addItemInteractor =
                new AddIncomeInteractorImpl(
                        mExecutor,
                        mMainThread,
                        year,
                        month,
                        amountCell,
                        description,
                        mRepository,
                        this
                );
        addItemInteractor.execute();
    }

    @Override
    public void onItemAdded() {
        mView.onItemAdded();
    }
}
