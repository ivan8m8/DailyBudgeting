package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.EditItemInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetItemByIdInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.EditIncomeInteractorImpl;
import ru.is88.dailybudgeting.domain.interactors.impl.GetItemByIdInteractorImpl;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditAccountPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditItemPresenter;

public class EditIncomePresenterImpl extends AbstractPresenter
        implements EditAccountPresenter, GetItemByIdInteractor.Callback<Income>, EditItemInteractor.Callback<Income> {

    private Repository<Income> mRepository;
    private EditItemPresenter.View<Income> mView;

    public EditIncomePresenterImpl(Executor executor, MainThread mainThread,
                                   Repository<Income> repository,
                                   EditItemPresenter.View<Income> view) {
        super(executor, mainThread);
        mView = view;
        mRepository = repository;
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
    public void onItemRetrieved(Income item) {
        mView.onItemRetrieved(item);
    }

    @Override
    public void onItemNotFound() {
        mView.showError("No income found");
    }

    @Override
    public void editAccount(AbstractAccount abstractAccountToEdit, Cell amountCell, String description) {
        EditItemInteractor editItemInteractor =
                new EditIncomeInteractorImpl(
                        mExecutor,
                        mMainThread,
                        (Income) abstractAccountToEdit,
                        amountCell,
                        description,
                        mRepository,
                        this
                );
        editItemInteractor.execute();
    }

    @Override
    public void onItemUpdated(Income item) {
        mView.onItemUpdated(item);
    }
}
