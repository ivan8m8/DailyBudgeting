package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.AddItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.AddFixedExpenseInteractorImpl;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AddAccountPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AddItemPresenter;

public class AddFixedExpensePresenterImpl extends AbstractPresenter
        implements AddItemPresenter, AddAccountPresenter, AddItemInteractor.Callback {

    private Repository<FixedExpense> mRepository;
    private AddItemPresenter.View mView;

    public AddFixedExpensePresenterImpl(Executor executor, MainThread mainThread,
                                        Repository<FixedExpense> repository,
                                        AddItemPresenter.View view) {
        super(executor, mainThread);
        mRepository = repository;
        mView = view;
    }

    @Override
    public void addNewAccount(int year, int month, double amount, String description) {
        AddItemInteractor addItemInteractor =
                new AddFixedExpenseInteractorImpl(
                        mExecutor,
                        mMainThread,
                        year,
                        month,
                        amount,
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