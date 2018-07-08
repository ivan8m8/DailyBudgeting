package ru.is88.dailybudgeting.storage;


import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.repositories.MonthDayRepository;
import ru.is88.dailybudgeting.storage.converters.StorageModelConverter;
import ru.is88.dailybudgeting.storage.model.TableMonthDay;
import ru.is88.dailybudgeting.storage.model.TableMonthDay_Table;

public class MonthDayRepositoryImpl implements MonthDayRepository {

    @Override
    public void update(MonthDay monthDay) {
        TableMonthDay dbItem = StorageModelConverter.convertToStorageModel(monthDay);
        dbItem.synced = false;
        dbItem.update();
        //TODO: Sync
    }

    @Override
    public void insert(MonthDay monthDay) {
        TableMonthDay dbItem = StorageModelConverter.convertToStorageModel(monthDay);
        dbItem.synced = false;
        dbItem.insert();
        //TODO: Sync
    }

    @Override
    public MonthDay getMonthDayById(int id) {
        TableMonthDay result = SQLite
                .select()
                .from(TableMonthDay.class)
                .where(TableMonthDay_Table.id.eq(id))
                .querySingle();
        if (result == null) {
            return null;
        }
        return StorageModelConverter.convertToDomainModel(result);
    }

    @Override
    public List<MonthDay> getMonthDayList(int month, int year) {
        List<TableMonthDay> result = SQLite
                .select()
                .from(TableMonthDay.class)
                .where(TableMonthDay_Table.month.eq(month))
                .and(TableMonthDay_Table.year.eq(year))
                .queryList();
        return StorageModelConverter.convertListToRecyclerModel(result);
    }
}
