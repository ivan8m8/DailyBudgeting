package ru.is88.dailybudgeting.presentation.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.presentation.ui.adapters.MyFragmentPagerAdapter;
import ru.is88.dailybudgeting.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(Utils.VIEW_PAGER_START_POSITION);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateAppBar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar); // delete, there is no toolbar for the activity
        setSupportActionBar(toolbar);

        updateAppBar(Utils.VIEW_PAGER_START_POSITION);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    void updateAppBar(int position){

        Toolbar monthToolbar = findViewById(R.id.monthToolbar);
        TextView incomeTextView = findViewById(R.id.incomeAmount);
        TextView expensesTextView = findViewById(R.id.expensesAmount);

        Calendar calendar = Calendar.getInstance();

        int monthDelta = position - Utils.VIEW_PAGER_START_POSITION;
        calendar.add(Calendar.MONTH, monthDelta);

        monthToolbar.setTitle(getMonthNameForTheCurrentFragment(calendar) + " " + calendar.get(Calendar.YEAR));
    }

    private String getMonthNameForTheCurrentFragment(Calendar calendar) {
        return DateUtils.formatDateTime(this, calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_MONTH_DAY | DateUtils.FORMAT_NO_YEAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
