package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.is88.dailybudgeting.presentation.ui.fragments.FixedExpensesPageFragment;
import ru.is88.dailybudgeting.presentation.ui.fragments.IncomePageFragment;

public class AccountsFragmentPagerAdapter extends FragmentPagerAdapter {

    private int mYear;
    private int mMonth;

    public AccountsFragmentPagerAdapter(FragmentManager fm, int year, int month) {
        super(fm);
        mYear = year;
        mMonth = month;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position) {
            case 0: return IncomePageFragment.newInstance(mYear, mMonth);
            case 1: return FixedExpensesPageFragment.newInstance(mYear, mMonth);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
