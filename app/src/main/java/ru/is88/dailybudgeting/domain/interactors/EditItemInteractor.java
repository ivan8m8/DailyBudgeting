package ru.is88.dailybudgeting.domain.interactors;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;

public interface EditItemInteractor extends Interactor {

    interface Callback<T> {
        void onItemUpdated(T item);
    }
}
