package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.domain.models.Cell;

public interface EditMonthDayPresenter {

    void getMonthDayById(int monthDayId);
    void editMonthDay(int monthDayId, Cell amountCell, String description);
}
