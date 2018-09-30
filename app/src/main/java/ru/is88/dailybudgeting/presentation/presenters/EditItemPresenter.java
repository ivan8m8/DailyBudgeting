package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface EditItemPresenter {

    interface View<T> extends BaseView {
        void onItemRetrieved(T item); // so that the user is able to see what he is going to edit
        void onItemUpdated(T item);
    }

}
