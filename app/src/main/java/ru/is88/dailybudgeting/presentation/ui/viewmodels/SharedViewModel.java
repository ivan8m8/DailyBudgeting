package ru.is88.dailybudgeting.presentation.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ru.is88.dailybudgeting.Utils;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public class SharedViewModel extends ViewModel {

    public MutableLiveData<Income> addedIncome = new MutableLiveData<>();
    public MutableLiveData<Utils.Pair<Income, Integer>> editedIncome = new MutableLiveData<>();

    public void setAddedIncome(Income income) {
        addedIncome.setValue(income);
    }

    public void setEditedIncome(Income income, int pos) {
        editedIncome.setValue(new Utils.Pair<>(income, pos));
    }
}
