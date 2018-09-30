package ru.is88.dailybudgeting.storage.converters;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.storage.model.TableFixedExpense;
import ru.is88.dailybudgeting.storage.model.TableIncome;
import ru.is88.dailybudgeting.storage.model.TableMonthDay;
import ru.is88.dailybudgeting.utils.Utils;

public class StorageModelConverter {

    @NonNull
    public static TableMonthDay convertToStorageModel(@NonNull MonthDay monthDay){
        TableMonthDay result = new TableMonthDay();
        result.setId(monthDay.getId());
        result.setYear(monthDay.getYear());
        result.setMonth(monthDay.getMonth());
        result.setDay(monthDay.getDay());
        result.setAmountString(monthDay.getAmountString());
        result.setDesc(monthDay.getDescription());
        return result;
    }

    /*
    Can not using the wildcard <? extends AbstractAccount> for the following 2 methods,
    since the method convertToDomainModel works with the specific table classes
    TableIncome & TableFixedExpense that do not extend any related abstract class.
     */
    @NonNull
    public static TableIncome convertToStorageModel(@NonNull Income income) {
        TableIncome result = new TableIncome();
        result.setId(income.getId());
        result.setYear(income.getYear());
        result.setMonth(income.getMonth());
        result.setAmount(income.getAmount());
        result.setDescription(income.getDescription());
        return result;
    }

    @NonNull
    public static TableFixedExpense convertToStorageModel(@NonNull FixedExpense fixedExpense) {
        TableFixedExpense result = new TableFixedExpense();
        result.setId(fixedExpense.getId());
        result.setYear(fixedExpense.getYear());
        result.setMonth(fixedExpense.getMonth());
        result.setAmount(fixedExpense.getAmount());
        result.setDescription(fixedExpense.getDescription());
        return result;
    }

    public static MonthDay convertToDomainModel(TableMonthDay tableMonthDay) {
        return new MonthDay(
                tableMonthDay.getId(),
                tableMonthDay.getAmountString(),
                tableMonthDay.getDesc()
        );
    }

    public static Income convertToDomainModel(TableIncome tableIncome) {
        return new Income(
                tableIncome.getId(),
                tableIncome.getYear(),
                tableIncome.getMonth(),
                tableIncome.getAmount(),
                tableIncome.getDescription()
        );
    }

    public static FixedExpense convertToDomainModel(TableFixedExpense tableFixedExpense) {
        return new FixedExpense(
                tableFixedExpense.getId(),
                tableFixedExpense.getYear(),
                tableFixedExpense.getMonth(),
                tableFixedExpense.getAmount(),
                tableFixedExpense.getDescription()
        );
    }

    @NonNull
    public static List<MonthDay> convertMonthDayListToRecyclerModel(List<TableMonthDay> tableMonthDays) {

        Calendar calendar = new GregorianCalendar(
                tableMonthDays.get(0).getYear(),
                tableMonthDays.get(0).getMonth(),
                tableMonthDays.get(0).getDay());

        List<MonthDay> result = new ArrayList<>();

        /*
         * It's implemented to populate the recycler mView,
         * otherwise, it'd be hard to parse empty month days within onBindViewHolder.
         */
        int j = 0;
        for (int i=0; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            if (j < tableMonthDays.size() && tableMonthDays.get(j).getDay() == i + 1) {
                result.add(convertToDomainModel(tableMonthDays.get(j)));
                j++;
            } else {
                result.add(new MonthDay(
                        Utils.buildMonthDayID(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i+1),
                        "", ""));
            }
        }

        //cleanup
        tableMonthDays.clear();

        return result;
    }

    /*
    Can not using overloading, because it causes clashing & same erasure in this case.
     */
    public static List<Income> convertIncomeListToDomainModel(List<TableIncome> tableIncomes) {

        List<Income> result = new ArrayList<>();

        for (TableIncome tableIncome : tableIncomes) {
            result.add(convertToDomainModel(tableIncome));
        }

        //cleanup
        tableIncomes.clear();

        return result;
    }

    public static List<FixedExpense> convertFixedExpenseListToDomainModel(List<TableFixedExpense> tableFixedExpenses) {

        List<FixedExpense> result = new ArrayList<>();

        for (TableFixedExpense tableFixedExpense : tableFixedExpenses) {
            result.add(convertToDomainModel(tableFixedExpense));
        }

        tableFixedExpenses.clear();

        return result;
    }
}
