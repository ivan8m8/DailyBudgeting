package ru.is88.dailybudgeting.domain.interactors;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.MonthDay;

public interface EditMonthDayInteractor extends Interactor {

    interface Callback {
        void onMonthDayUpdated(MonthDay monthDay);
    }
}
