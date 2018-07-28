package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.GetAccountByIdInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.EditIncomeInteractorImpl;
import ru.is88.dailybudgeting.domain.interactors.impl.GetAccountByIdInteractorImpl;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.domain.interactors.EditAccountInteractor;
import ru.is88.dailybudgeting.presentation.presenters.EditAccountPresenter;

public class EditIncomePresenterImpl extends AbstractPresenter
        implements EditAccountPresenter, GetAccountByIdInteractor.Callback, EditAccountInteractor.Callback {

    private EditAccountPresenter.View view;
    private AccountRepository accountRepository;

    public EditIncomePresenterImpl(Executor executor, MainThread mainThread,
                                   View view,
                                   AccountRepository accountRepository) {
        super(executor, mainThread);

        this.view = view;
        this.accountRepository = accountRepository;
    }

    @Override
    public void getAccountById(long id) {
        GetAccountByIdInteractor getAccountByIdInteractor =
                new GetAccountByIdInteractorImpl(
                        executor,
                        mainThread,
                        id,
                        accountRepository,
                        this
                );
        getAccountByIdInteractor.execute();
    }

    @Override
    public void onAccountRetrieved(AbstractAccount abstractAccount) {
        view.onAccountRetrieved(abstractAccount);
    }

    @Override
    public void onAccountNotFound() {
        view.showError("No account found");
    }

    @Override
    public void editAccount(AbstractAccount abstractAccount, String description, double amount, int yearMonth) {
        EditAccountInteractor editAccountInteractor =
                new EditIncomeInteractorImpl(
                        executor,
                        mainThread,
                        (Income) abstractAccount,
                        description,
                        amount,
                        yearMonth,
                        this,
                        accountRepository
                );
        editAccountInteractor.execute();
    }

    @Override
    public void onAccountUpdated(AbstractAccount abstractAccount) {
        view.onAccountUpdated(abstractAccount);
    }
}
