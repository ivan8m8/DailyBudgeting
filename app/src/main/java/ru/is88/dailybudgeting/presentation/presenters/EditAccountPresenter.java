package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface EditAccountPresenter {

    interface View extends BaseView {
        void onAccountRetrieved(AbstractAccount abstractAccount);
        void onAccountUpdated(AbstractAccount abstractAccount);
    }

    void getAccountById(long id);
    void editAccount(AbstractAccount abstractAccount, String description, double amount, int yearMonth);
}
