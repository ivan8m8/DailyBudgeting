package ru.is88.dailybudgeting.domain.interactors;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;

public interface GetAccountByIdInteractor extends Interactor {

    interface Callback {
        void onAccountRetrieved(AbstractAccount abstractAccount);
        void onAccountNotFound();
    }
}