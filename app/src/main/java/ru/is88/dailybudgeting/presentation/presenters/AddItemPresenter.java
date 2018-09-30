package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface AddItemPresenter {

    interface View extends BaseView {
        void onItemAdded();
    }
}
