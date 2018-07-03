package ru.is88.dailybudgeting.storage.converters;

import android.support.annotation.NonNull;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.storage.model.TableMonthDay;

public class StorageModelConverter {

    public static TableMonthDay convertToStorageModel(MonthDay monthDay){
        TableMonthDay result = new TableMonthDay();
        result.setId(monthDay.getId());
        result.setDesc(monthDay.getDescription());
        result.setAmountString(monthDay.getAmountString());
        return result;
    }

    public static MonthDay convertToDomainModel(TableMonthDay tableMonthDay) {
        return new MonthDay(
                tableMonthDay.getId(),
                tableMonthDay.getAmountString(),
                tableMonthDay.getDesc()
        );
    }
}
