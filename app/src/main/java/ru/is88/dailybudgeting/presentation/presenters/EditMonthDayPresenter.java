package ru.is88.dailybudgeting.presentation.presenters;

public interface EditMonthDayPresenter {

//    interface View extends BaseView {
//        void onMonthDayRetrieved(MonthDay monthDay);
//        void onMonthDayUpdated(MonthDay monthDay);
//    }

    void getMonthDayById(int monthDayId);
    void editMonthDay(int monthDayId, String amountString, String description);
}
