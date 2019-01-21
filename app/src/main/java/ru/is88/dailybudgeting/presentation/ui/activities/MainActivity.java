package ru.is88.dailybudgeting.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.presentation.ui.adapters.MonthDaysFragmentPagerAdapter;
import ru.is88.dailybudgeting.Utils;

public class MainActivity extends AppCompatActivity {

    //TODO: make the recyclers cards clickable
    //TODO: allow users to choose the app bar color through the preferences

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // the need of the following is to show the three dots menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.monthDaysViewPager);
        MonthDaysFragmentPagerAdapter monthDaysFragmentPagerAdapter = new MonthDaysFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(monthDaysFragmentPagerAdapter);

        //TODO: when the user navigates back to MainActivity, he should be shown an appropriate month page fragment
        mViewPager.setCurrentItem(Utils.VIEW_PAGER_START_POSITION);

        // Violation of ISP by Google Android :)
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                updateAppBar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountsActivity.class);
                intent.putExtra(Utils.YEAR_KEY, Utils.getActualCalendarByViewPagerPosition(mViewPager.getCurrentItem()).get(Calendar.YEAR));
                intent.putExtra(Utils.MONTH_KEY, Utils.getActualCalendarByViewPagerPosition(mViewPager.getCurrentItem()).get(Calendar.MONTH));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateAppBar(mViewPager.getCurrentItem());
    }

    private void updateAppBar(int viewPagerPosition){

        Toolbar monthToolbar = findViewById(R.id.monthToolbar);
        TextView incomeTextView = findViewById(R.id.incomeAmount);
        TextView expensesTextView = findViewById(R.id.expensesAmount);

        Calendar calendar = Utils.getActualCalendarByViewPagerPosition(viewPagerPosition);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
