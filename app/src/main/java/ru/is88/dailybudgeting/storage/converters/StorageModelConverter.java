package ru.is88.dailybudgeting.storage.converters;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public static List<MonthDay> convertListToDomainModel(List<TableMonthDay> tableMonthDays) {
        List<MonthDay> result = new ArrayList<>();

        for (TableMonthDay tableMonthDay : tableMonthDays) {
            result.add(convertToDomainModel(tableMonthDay));
        }

        return result;
    }
}
