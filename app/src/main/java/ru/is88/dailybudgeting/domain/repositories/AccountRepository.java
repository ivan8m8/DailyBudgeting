package ru.is88.dailybudgeting.domain.repositories;

import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;

public interface AccountRepository {

    void insert(AbstractAccount abstractAccount);
    void update(AbstractAccount abstractAccount);

    AbstractAccount getAccountById(long id);
}
