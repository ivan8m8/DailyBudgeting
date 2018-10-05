package ru.is88.dailybudgeting.presentation.presenters;

import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;

public interface EditAccountPresenter {

    void getAccountById(long id);
    void editAccount(AbstractAccount abstractAccountToEdit, Cell amountCell, String description);
}
