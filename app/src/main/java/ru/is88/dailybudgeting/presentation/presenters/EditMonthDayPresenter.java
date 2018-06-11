package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface EditMonthDayPresenter {

    interface View extends BaseView {
        void onMonthDayRetrieved(MonthDay monthDay);
        void onMonthDayUpdated(MonthDay monthDay);
    }

    void getMonthDayById(int id);
    void editMonthDay(MonthDay monthDay, String description, String amount);
}
