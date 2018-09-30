package ru.is88.dailybudgeting.storage;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.storage.converters.StorageModelConverter;
import ru.is88.dailybudgeting.storage.model.TableFixedExpense;
import ru.is88.dailybudgeting.storage.model.TableFixedExpense_Table;

public class FixedExpenseRepositoryImpl implements Repository<FixedExpense> {

    @Override
    public void insert(@NonNull FixedExpense item) {
        TableFixedExpense dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.insert();
        dbItem.synced = false;
        //TODO: Sync
    }

    @Override
    public void update(@NonNull FixedExpense item) {
        TableFixedExpense dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.update();
        dbItem.synced = false;
        //TODO: Sync
    }

    @Override
    public FixedExpense getItemById(long id) {
        TableFixedExpense result = SQLite
                .select()
                .from(TableFixedExpense.class)
                .where(TableFixedExpense_Table.id.eq(id))
                .querySingle();
        if (result == null) {
            return null;
        }
        return StorageModelConverter.convertToDomainModel(result);
    }

    @Override
    public List<FixedExpense> getItemList(int year, int month) {
        List<TableFixedExpense> result = SQLite
                .select()
                .from(TableFixedExpense.class)
                .where(TableFixedExpense_Table.year.eq(year))
                .and(TableFixedExpense_Table.month.eq(month))
                .queryList();
        return StorageModelConverter.convertFixedExpenseListToDomainModel(result);
    }
}
