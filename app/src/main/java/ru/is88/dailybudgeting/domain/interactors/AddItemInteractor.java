package ru.is88.dailybudgeting.domain.interactors;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;

public interface AddItemInteractor extends Interactor {

    interface Callback {
        void onItemAdded();
    }
}
