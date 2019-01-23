package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetItemByIdInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.EditFixedExpenseInteractorImpl;
import ru.is88.dailybudgeting.domain.interactors.impl.GetItemByIdInteractorImpl;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditAccountPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditItemPresenter;

public class EditFixedExpensePresenterImpl extends AbstractPresenter
        implements EditAccountPresenter, EditItemInteractor.Callback<FixedExpense>, GetItemByIdInteractor.Callback<FixedExpense> {

    private Repository<FixedExpense> mRepository;
    private EditItemPresenter.View<FixedExpense> mView;

    public EditFixedExpensePresenterImpl(Executor executor, MainThread mainThread,
                                         Repository<FixedExpense> repository,
                                         EditItemPresenter.View<FixedExpense> view) {
        super(executor, mainThread);
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getAccountById(long id) {
        GetItemByIdInteractor getItemByIdInteractor =
                new GetItemByIdInteractorImpl<>(
                        mExecutor,
                        mMainThread,
                        id,
                        mRepository,
                        this
                );
        getItemByIdInteractor.execute();
    }

    @Override
    public void onItemRetrieved(FixedExpense item) {
        mView.onItemRetrieved(item);
    }

    @Override
    public void onItemNotFound() {
        mView.showError("No fixed expense found");
    }

    @Override
    public void editAccount(long id, Cell amountCell, String description) {
        EditItemInteractor editItemInteractor =
                new EditFixedExpenseInteractorImpl(
                        mExecutor,
                        mMainThread,
                        id,
                        amountCell,
                        description,
                        mRepository,
                        this
                );
        editItemInteractor.execute();
    }

    @Override
    public void onItemUpdated(FixedExpense item) {
        mView.onItemUpdated(item);
    }
}
