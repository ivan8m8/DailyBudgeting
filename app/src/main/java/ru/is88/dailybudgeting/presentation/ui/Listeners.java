package ru.is88.dailybudgeting.presentation.ui;

import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.domain.models.accounts.Income;

public interface Listeners {

    interface OnRecyclerViewItemClick {
        void onClick(int position);
    }

    interface OnItemEdited<T> {
        void onItemEdited(T item, int pos);
    }

    interface OnItemAdded<T> {
        void onItemAdded(T item);
    }

    // Unable to use something like OnItemAdded<T>. Otherwise, it causes
    // the unchecked cast warning, since Java cannot cast generic types
    // in the Runtime, while I have to initialize my callback withing onAttach().
    // An example code that causes this warning:
    // mCallback = (Listeners.OnItemAdded<Income>) context;
    //
    // Also unable to extend OnItemAdded<T>. Otherwise, it causes the error
    // 'OnItemAdded cannot be inherited with different type arguments Income or FixedExpense'.
    // More: https://stackoverflow.com/a/19436554/7541231. Remember about the type erasure.
    //
    // In result, I decided to move the showing add & edit dialog fragments logic
    // from AccountsActivity to each Fragment

    interface OnMonthDayEdited extends OnItemEdited<MonthDay> {
        @Override
        void onItemEdited(MonthDay item, int pos);
    }

    interface OnIncomeEdited extends OnItemEdited<Income> {
        @Override
        void onItemEdited(Income item, int pos);
    }

    interface OnFixedExpenseEdited extends OnItemEdited<FixedExpense> {
        @Override
        void onItemEdited(FixedExpense item, int pos);
    }

    interface OnIncomeAdded extends OnItemAdded<Income> {
        @Override
        void onItemAdded(Income item);
    }

    interface OnFixedExpenseAdded extends OnItemAdded<FixedExpense> {
        @Override
        void onItemAdded(FixedExpense item);
    }
}
