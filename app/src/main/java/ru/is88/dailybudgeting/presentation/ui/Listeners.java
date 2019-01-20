package ru.is88.dailybudgeting.presentation.ui;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public interface Listeners {

    interface OnRecyclerViewItemClick {
        void onClick(int position);
    }

    interface OnMonthDayEditingFinished {
        void onMonthDayEditingFinished(MonthDay monthDay, int position);
    }

    interface OnIncomeAdded {
        void onIncomeAdded(Income income);
    }
}
