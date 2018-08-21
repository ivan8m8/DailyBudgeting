package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.is88.dailybudgeting.presentation.ui.fragments.MonthDaysPageFragment;
import ru.is88.dailybudgeting.utils.Utils;

public class MonthDaysFragmentPagerAdapter extends FragmentPagerAdapter {

    public MonthDaysFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int monthDelta = position - Utils.VIEW_PAGER_START_POSITION;
        return MonthDaysPageFragment.newInstance(monthDelta);
    }

    @Override
    public int getCount() {
        return Utils.VIEW_PAGER_ITEMS_COUNT;
    }
}
