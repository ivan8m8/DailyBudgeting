package ru.is88.dailybudgeting.storage;

import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.domain.repositories.AccountRepository;
import ru.is88.dailybudgeting.storage.converters.StorageModelConverter;
import ru.is88.dailybudgeting.storage.model.TableIncome;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public void insert(Income income) {
        TableIncome dbItem = StorageModelConverter.convertToStorageModel(income);
        dbItem.synced = false;
        dbItem.insert();
        //TODO: Sync
    }

    @Override
    public void update(Income income) {
        TableIncome dbItem = StorageModelConverter.convertToStorageModel(income);
        dbItem.synced = false;
        dbItem.update();
        //TODO: Sync
    }

    @Override
    public void insert(FixedExpense fixedExpense) {

    }

    @Override
    public void update(FixedExpense fixedExpense) {

    }

    @Override
    public AbstractAccount getAccountById(long id) {
        return null;
    }
}
