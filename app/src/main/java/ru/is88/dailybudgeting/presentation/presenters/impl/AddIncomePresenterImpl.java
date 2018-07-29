package ru.is88.dailybudgeting.presentation.presenters.impl;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.AddAccountInteractor;
import ru.is88.dailybudgeting.domain.interactors.impl.AddIncomeInteractorImpl;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;
import ru.is88.dailybudgeting.presentation.presenters.AbstractPresenter;
import ru.is88.dailybudgeting.presentation.presenters.AddAccountPresenter;

public class AddIncomePresenterImpl extends AbstractPresenter
        implements AddAccountPresenter, AddAccountInteractor.Callback {

    private AddAccountPresenter.View view;
    private AccountRepository accountRepository;

    public AddIncomePresenterImpl(Executor executor, MainThread mainThread,
                                  View view,
                                  AccountRepository accountRepository) {
        super(executor, mainThread);

        this.view = view;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addNewAccount(String description, double amount, int year, int month) {
        AddAccountInteractor addAccountInteractor = new
                AddIncomeInteractorImpl(
                        executor,
                mainThread,
                description,
                amount,
                year,
                month,
                accountRepository,
                this
        );
        addAccountInteractor.execute();
    }

    @Override
    public void onAccountAdded() {
        view.onAccountAdded();
    }
}
