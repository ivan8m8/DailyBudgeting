package ru.is88.dailybudgeting.presentation.presenters;

public interface AddAccountPresenter {

//    interface View extends BaseView {
//        void onAccountAdded();
//    }

    void addNewAccount(int year, int month, double amount, String description);
}
