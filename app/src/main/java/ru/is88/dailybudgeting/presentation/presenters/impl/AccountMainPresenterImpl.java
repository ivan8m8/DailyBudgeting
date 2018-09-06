package ru.is88.dailybudgeting.presentation.presenters.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.GetAccountListInteractor;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AccountMainPresenter;

public class AccountMainPresenterImpl extends AbstractPresenter implements AccountMainPresenter, GetAccountListInteractor.Callback {

    private AccountRepository accountRepository;
    private AccountMainPresenter.View view;

    public AccountMainPresenterImpl(Executor executor, MainThread mainThread,
                                    AccountRepository accountRepository,
                                    AccountMainPresenter.View view) {
        super(executor, mainThread);

        this.accountRepository = accountRepository;
        this.view = view;
    }

    @Override
    public void getAccountList(int month, int year) {
        GetAccountListInteractor getAccountListInteractor;
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

    @Override
    public void onAccountListRetrieved(List<? extends AbstractAccount> accounts) {

    }
}
