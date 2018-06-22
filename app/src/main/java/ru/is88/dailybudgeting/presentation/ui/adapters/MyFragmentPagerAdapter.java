package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.is88.dailybudgeting.presentation.ui.fragments.PageFragment;
import ru.is88.dailybudgeting.utils.Utils;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int delta = position - Utils.VIEW_PAGER_START_POSITION;
        return PageFragment.newInstance(delta);
    }

    @Override
    public int getCount() {
        return Utils.VIEW_PAGER_ITEMS_COUNT;
    }
}
