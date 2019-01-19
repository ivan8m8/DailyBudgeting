package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.AddItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.AddFixedExpenseInteractorImpl;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AddAccountPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AddItemPresenter;

public class AddFixedExpensePresenterImpl extends AbstractPresenter
        implements AddItemPresenter, AddAccountPresenter, AddItemInteractor.Callback<FixedExpense> {

    private Repository<FixedExpense> mRepository;
    private AddItemPresenter.View<FixedExpense> mView;

    public AddFixedExpensePresenterImpl(Executor executor, MainThread mainThread,
                                        Repository<FixedExpense> repository,
                                        AddItemPresenter.View<FixedExpense> view) {
        super(executor, mainThread);
        mRepository = repository;
        mView = view;
    }

    @Override
    public void addNewAccount(int year, int month, Cell amountCell, String description) {
        AddItemInteractor addItemInteractor =
                new AddFixedExpenseInteractorImpl(
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
    public void onItemAdded(FixedExpense fixedExpense) {
        mView.onItemAdded(fixedExpense);
    }
}
