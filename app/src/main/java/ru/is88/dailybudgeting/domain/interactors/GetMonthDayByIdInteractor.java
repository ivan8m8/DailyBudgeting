package ru.is88.dailybudgeting.domain.interactors;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.MonthDay;

public interface GetMonthDayByIdInteractor extends Interactor {

    interface Callback {
        void onMonthDayRetrieved(MonthDay monthDay);
        void onMonthDayNotFound();
    }
}
