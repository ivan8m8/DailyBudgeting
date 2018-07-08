package ru.is88.dailybudgeting.storage.converters;


import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.storage.model.TableMonthDay;

public class StorageModelConverter {

    public static TableMonthDay convertToStorageModel(MonthDay monthDay){
        TableMonthDay result = new TableMonthDay();
        result.setId(monthDay.getId());
        result.setDesc(monthDay.getDescription());
        result.setAmountString(monthDay.getAmountString());
        result.setDay(monthDay.getDay());
        result.setMonth(monthDay.getMonth());
        result.setYear(monthDay.getYear());
        return result;
    }

    public static MonthDay convertToDomainModel(TableMonthDay tableMonthDay) {
        return new MonthDay(
                tableMonthDay.getId(),
                tableMonthDay.getAmountString(),
                tableMonthDay.getDesc()
        );
    }

    public static List<MonthDay> convertListToRecyclerModel(List<TableMonthDay> tableMonthDays) {

        Calendar calendar = new GregorianCalendar(
                tableMonthDays.get(0).getYear(),
                tableMonthDays.get(0).getMonth(),
                tableMonthDays.get(0).getDay());

        List<MonthDay> result = new ArrayList<>();

        for (int i=0; i < tableMonthDays.size() - 1; i++){
            result.add(convertToDomainModel(tableMonthDays.get(i)));
        }

        for (int i=0; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1; i++) {

            for (int j=0; j < result.size(); j++) {
                // comparator
            }


//            if (tableMonthDays.get(i) != null) {
//                Log.d("KSI", " added null");
//            } else {
//                Log.d("KSI", " added NOT null");
//            }

//            if (tableMonthDays.get(i) != null && tableMonthDays.get(i).getDay() - 1 == i) {
//                result.add(convertToDomainModel(tableMonthDays.get(i)));
//                Log.d("KSI", " added NOT null");
//            } else {
//                result.add(null);
//                Log.d("KSI", " added null");
//            }
        }

//        for (TableMonthDay tableMonthDay : tableMonthDays) {
//            result.add(convertToDomainModel(tableMonthDay));
//        }

        Log.d("KSI", " size " + String.valueOf(result.size()));

        return result;
    }
}
