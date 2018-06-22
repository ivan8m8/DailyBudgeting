package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.presentation.ui.adapters.MonthDaysRecyclerAdapter;
import ru.is88.dailybudgeting.utils.Utils;

public class PageFragment extends Fragment {

    private static final String MONTH_KEY = "month_key";

    private int mMonth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMonth = getArguments().getInt(MONTH_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_page, container, false);

        Calendar calendar = Calendar.getInstance();

        Toolbar toolbar = viewRoot.findViewById(R.id.monthToolbar);
        boolean previousYear = mMonth <= 0;
        if (previousYear) {
            mMonth = mMonth + calendar.getActualMaximum(Calendar.MONTH) + 1;
        }
        String monthTitle = new DateFormatSymbols().getMonths()[mMonth - 1];
        toolbar.setTitle(monthTitle);

        RecyclerView recyclerView = viewRoot.findViewById(R.id.monthDaysRecyclerView);
        MonthDaysRecyclerAdapter monthDaysRecyclerAdapter = new MonthDaysRecyclerAdapter();
        recyclerView.setAdapter(monthDaysRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.scrollToPosition(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        return viewRoot;
    }

    /**
     * This method is called within MyFragmentPagerAdapter.getItem(int position)
     * @param month !!!
     */
    public static PageFragment newInstance(int month) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MONTH_KEY, month);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }
}
