package ru.is88.dailybudgeting.presentation.presenters;

import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface MonthDayMainPresenter extends BasePresenter {

    interface View extends BaseView {

        void showMonthDays(List<MonthDay> monthDays);
        void onClickEditMonthDay(int monthDayId, int position);
    }

    void getMonthDayList(int month, int year);
}
