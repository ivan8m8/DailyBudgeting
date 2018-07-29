package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface AddAccountPresenter {

    interface View extends BaseView {
        void onAccountAdded();
    }

    void addNewAccount(String description, double amount, int year, int month);
}
