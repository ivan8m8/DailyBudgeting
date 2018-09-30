package ru.is88.dailybudgeting.domain.interactors;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;

public interface GetItemByIdInteractor extends Interactor {

    interface Callback<T> {
        void onItemRetrieved(T item);
        void onItemNotFound();
    }
}
