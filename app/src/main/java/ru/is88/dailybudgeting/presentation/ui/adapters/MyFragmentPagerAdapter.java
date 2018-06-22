package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.Calendar;

import ru.is88.dailybudgeting.presentation.ui.fragments.PageFragment;
import ru.is88.dailybudgeting.utils.Utils;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private int currentMonth;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.currentMonth = Calendar.getInstance().get(Calendar.MONTH) -4; // because months in Java are 0-indexed
    }

    @Override
    public Fragment getItem(int position) {
        int month = currentMonth + position - Utils.VIEW_PAGER_START_POSITION;
        return PageFragment.newInstance(month);
    }

    @Override
    public int getCount() {
        return Utils.VIEW_PAGER_ITEMS_COUNT;
    }
}
