package ru.is88.dailybudgeting.domain.interactors;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;

public interface AddAccountInteractor extends Interactor {

    interface Callback {
        void onAccountAdded();
    }
}
