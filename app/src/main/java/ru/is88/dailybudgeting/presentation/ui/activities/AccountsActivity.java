package ru.is88.dailybudgeting.presentation.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.presentation.ui.adapters.AccountsFragmentPagerAdapter;
import ru.is88.dailybudgeting.utils.Utils;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final int year = getIntent().getIntExtra(Utils.YEAR_KEY, Utils.DEFAULT_VALUE);
        final int month = getIntent().getIntExtra(Utils.MONTH_KEY, Utils.DEFAULT_VALUE);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        final AccountsFragmentPagerAdapter accountsFragmentPagerAdapter
                = new AccountsFragmentPagerAdapter(getSupportFragmentManager(), year, month);

        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = findViewById(R.id.accountsViewPager);
        viewPager.setAdapter(accountsFragmentPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mViewPager.getCurrentItem() == 0) {
//                    AddIncomeDialogFragment addIncomeDialogFragment = AddIncomeDialogFragment.newInstance(year, month);
//                    addIncomeDialogFragment.show(getSupportFragmentManager(), "add_income_dialog_fragment");
//
//                    // sets IncomePageFragment as the target Fragment for AddIncomeDialogFragment, to
//                    // be able to retrieve the just added Income from AddIncomeDialogFragment
//                    //addIncomeDialogFragment.setTargetFragment(accountsFragmentPagerAdapter.getItem(0), 0);
//                } else if (mViewPager.getCurrentItem() == 1) {
//                    AddFixedExpenseDialogFragment addFixedExpenseDialogFragment = AddFixedExpenseDialogFragment.newInstance(year, month);
//                    addFixedExpenseDialogFragment.show(getSupportFragmentManager(), "add_fixed_expense_dialog_fragment");
//                    //addFixedExpenseDialogFragment.setTargetFragment(accountsFragmentPagerAdapter.getItem(1), 0);
//                }
//            }
//        });
    }

//    @Override
//    public void onItemAdded(Income income) {
//        Fragment f = getSupportFragmentManager()
//                .findFragmentByTag("android:switcher:" + R.id.accountsViewPager + ":" + Utils.INCOME_PAGE_FRAGMENT_ID);
//        if (f != null) {
//            ((IncomePageFragment) f).notifyAdapterItemInserted(income);
//        }
//    }
}
