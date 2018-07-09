package ru.is88.dailybudgeting.storage.converters;

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

        /*
         * It's implemented to populate recycler view,
         * otherwise, it'd be hard to parse empty month days within onBindViewHolder.
         */
        int j = 0;
        for (int i=0; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            if (j < tableMonthDays.size() && tableMonthDays.get(j).getDay() == i + 1) {
                result.add(convertToDomainModel(tableMonthDays.get(j)));
                j++;
            } else {
                result.add(new MonthDay(buildMonthDayID(calendar, i+1), "", ""));
            }
        }

        //Log.d("KSI", " size " + String.valueOf(result.size()));

        return result;
    }

    /**
     * Unable to move this function to Utils and make it general,
     * because another one, which is within MonthDaysRecyclerAdapter,
     * does Calendar.month + 1 to put correct value to the DB,
     * while this one retrieves from the DB the month, that has already been turned to human-readable view.
     */
    private static int buildMonthDayID(Calendar calendar, int day) {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        String idString = year +
                String.valueOf(month < 10 ? "0" : "") + month +
                String.valueOf(day < 10 ? "0" : "") + day;

        return Integer.parseInt(idString);
    }
}
