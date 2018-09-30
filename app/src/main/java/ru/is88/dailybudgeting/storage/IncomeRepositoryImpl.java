package ru.is88.dailybudgeting.storage;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.storage.converters.StorageModelConverter;
import ru.is88.dailybudgeting.storage.model.TableIncome;
import ru.is88.dailybudgeting.storage.model.TableIncome_Table;

public class IncomeRepositoryImpl implements Repository<Income> {

    @Override
    public void insert(@NonNull Income item) {
        TableIncome dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.synced = false;
        dbItem.insert();
        //TODO: Sync
    }

    @Override
    public void update(@NonNull Income item) {
        TableIncome dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.synced = false;
        dbItem.update();
        //TODO: Sync
    }

    @Override
    public Income getItemById(long id) {
        TableIncome result = SQLite
                .select()
                .from(TableIncome.class)
                .where(TableIncome_Table.id.eq(id))
                .querySingle();
        if (result == null) {
            return null;
        }
        return StorageModelConverter.convertToDomainModel(result);
    }

    @Override
    public List<Income> getItemList(int year, int month) {
        List<TableIncome> result = SQLite
                .select()
                .from(TableIncome.class)
                .where(TableIncome_Table.year.eq(year))
                .and(TableIncome_Table.month.eq(month))
                .queryList();
        return StorageModelConverter.convertIncomeListToDomainModel(result);
    }
}
