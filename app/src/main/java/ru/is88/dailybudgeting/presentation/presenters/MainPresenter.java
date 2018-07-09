package ru.is88.dailybudgeting.presentation.presenters;

import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {

        void showMonthDays(List<MonthDay> monthDays);

        void onClickEditMonthDay(final int monthDayId, final int position);
    }

    void getMonthDayList(int month, int year);
}
