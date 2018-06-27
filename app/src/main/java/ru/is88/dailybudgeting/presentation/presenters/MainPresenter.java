package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {

        void onClickEditMonthDay(int monthDayId, int position);
    }
}
