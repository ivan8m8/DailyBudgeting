package ru.is88.dailybudgeting.domain.interactors;

import java.util.List;

import ru.is88.dailybudgeting.domain.interactors.base.Interactor;

/*
    Actually, it'd be better to name this GetItemListByYearAndMonthInteractor
        or create the abstract object CalendarItem.
 */

public interface GetItemListInteractor extends Interactor {

    interface Callback<T> {
        void onItemListRetrieved(List<T> list);
    }
}
