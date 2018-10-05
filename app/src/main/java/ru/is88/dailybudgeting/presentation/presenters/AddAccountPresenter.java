package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.domain.models.Cell;

public interface AddAccountPresenter {

    void addNewAccount(int year, int month, Cell amountCell, String description);
}
