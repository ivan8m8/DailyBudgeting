package ru.is88.dailybudgeting.presentation.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.utils.Pair;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public class SharedViewModel extends ViewModel {

    public MutableLiveData<Income> addedIncome = new MutableLiveData<>();
    public MutableLiveData<Pair<Income, Integer>> editedIncome = new MutableLiveData<>();

    public MutableLiveData<FixedExpense> addedFixedExpense = new MutableLiveData<>();
    public MutableLiveData<Pair<FixedExpense, Integer>> editedFixedExpense = new MutableLiveData<>();

    public MutableLiveData<Pair<MonthDay, Integer>> editedMonthDay = new MutableLiveData<>();

    public void setAddedIncome(Income income) {
        addedIncome.setValue(income);
    }

    public void setEditedIncome(Income income, int pos) {
        editedIncome.setValue(new Pair<>(income, pos));
    }

    public void setAddedFixedExpense(FixedExpense fixedExpense) {
        addedFixedExpense.setValue(fixedExpense);
    }

    public void setEditedFixedExpense(FixedExpense fixedExpense, int pos) {
        editedFixedExpense.setValue(new Pair<>(fixedExpense, pos));
    }

    public void setEditedMonthDay(MonthDay monthDay, int pos) {
        editedMonthDay.setValue(new Pair<>(monthDay, pos));
    }
}
