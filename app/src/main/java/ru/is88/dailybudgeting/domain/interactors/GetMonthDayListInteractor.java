package ru.is88.dailybudgeting.domain.interactors;

import java.util.List;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;
import ru.is88.dailybudgeting.domain.models.MonthDay;

public interface GetMonthDayListInteractor extends Interactor {

    interface Callback {
        void onMonthDayListRetrieved(List<MonthDay> monthDays);
    }
}
