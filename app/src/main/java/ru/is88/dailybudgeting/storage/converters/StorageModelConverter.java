package ru.is88.dailybudgeting.storage.converters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.storage.model.TableIncome;
import ru.is88.dailybudgeting.storage.model.TableMonthDay;
import ru.is88.dailybudgeting.utils.Utils;

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

    public static TableIncome convertToStorageModel(Income income) {
        TableIncome result = new TableIncome();
        result.setId(income.getId());
        result.setDescription(income.getDescription());
        result.setAmount(income.getAmount());
        result.setYear(income.getYear());
        result.setMonth(income.getMonth());
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
                result.add(new MonthDay(
                        Utils.buildMonthDayID(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i+1),
                        "", ""));
            }
        }

        return result;
    }
}
