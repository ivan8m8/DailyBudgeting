package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.domain.models.Cell;

public interface EditAccountPresenter {

    void getAccountById(long id);
    void editAccount(long id, Cell amountCell, String description);
}
