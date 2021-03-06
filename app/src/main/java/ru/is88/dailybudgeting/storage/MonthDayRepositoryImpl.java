package ru.is88.dailybudgeting.storage;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.storage.converters.StorageModelConverter;
import ru.is88.dailybudgeting.storage.model.TableMonthDay;
import ru.is88.dailybudgeting.storage.model.TableMonthDay_Table;

public class MonthDayRepositoryImpl implements Repository<MonthDay> {

    @Override
    public void insert(@NonNull MonthDay item) {
        TableMonthDay dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.synced = false;
        dbItem.insert();
        //TODO: Sync
    }

    @Override
    public void update(@NonNull MonthDay item) {
        TableMonthDay dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.synced = false;
        dbItem.update();
        //TODO: Sync
    }

    @Override
    public MonthDay getItemById(long id) {
        TableMonthDay result = SQLite
                .select()
                .from(TableMonthDay.class)
                .where(TableMonthDay_Table.id.eq((int) id))
                .querySingle();
        if (result == null) {
            return null;
        }
        return StorageModelConverter.convertToDomainModel(result);
    }

    @Override
    public List<MonthDay> getItemList(int year, int month) {
        List<TableMonthDay> result = SQLite
                .select()
                .from(TableMonthDay.class)
                .where(TableMonthDay_Table.year.eq(year))
                .and(TableMonthDay_Table.month.eq(month))
                .queryList();
        return StorageModelConverter.convertMonthDayListToRecyclerModel(result);
    }
}
