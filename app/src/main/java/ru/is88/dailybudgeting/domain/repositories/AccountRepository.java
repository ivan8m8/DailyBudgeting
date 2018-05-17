package ru.is88.dailybudgeting.domain.repositories;

import ru.is88.dailybudgeting.domain.models.accounts.Account;

public interface AccountRepository {

    void insert(Account account);
    void update(Account account);
    void delete(Account account);
}
