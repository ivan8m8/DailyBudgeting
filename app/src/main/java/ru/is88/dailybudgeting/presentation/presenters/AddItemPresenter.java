package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface AddItemPresenter {

    interface View<T> extends BaseView {

        void onItemAdded(T item);

        @Override
        void onProgressStarted();

        @Override
        void onProgressFinished();
    }
}
