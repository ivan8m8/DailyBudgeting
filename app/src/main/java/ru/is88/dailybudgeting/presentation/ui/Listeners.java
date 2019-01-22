package ru.is88.dailybudgeting.presentation.ui;

public interface Listeners {

    interface OnRecyclerViewItemClick {
        void onClick(int position);
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
    // Then, I decided to move the showing add & edit dialog fragments logic
    // from AccountsActivity to each Fragment
    //
    // Finally, migrated to ViewModel to communicate between fragments
}
