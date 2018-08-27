package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.is88.dailybudgeting.presentation.ui.fragments.AccountsPageFragment;

public class AccountsFragmentPagerAdapter extends FragmentPagerAdapter {

    public AccountsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return AccountsPageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
