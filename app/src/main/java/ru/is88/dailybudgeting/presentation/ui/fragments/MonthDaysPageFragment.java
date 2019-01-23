package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.MonthDayMainPresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.adapters.MonthDaysRecyclerAdapter;
import ru.is88.dailybudgeting.presentation.ui.viewmodels.SharedViewModel;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;
import ru.is88.dailybudgeting.utils.Pair;
import ru.is88.dailybudgeting.utils.Utils;

public class MonthDaysPageFragment extends Fragment implements MainPresenter.View<MonthDay> {

    private static final String MONTH_DELTA_KEY = "month_delta_key";

    private Calendar mCalendar;

    private List<MonthDay> mMonthDays;
    private MonthDaysRecyclerAdapter mMonthDaysRecyclerAdapter;
    private RecyclerView mRecyclerView;

    private NestedScrollView mNestedScrollView;

    public MonthDaysPageFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int monthDelta;
        if (getArguments() != null) {
            monthDelta = getArguments().getInt(MONTH_DELTA_KEY);
        } else {
            monthDelta = 0;
            // handle a mistake. The PageFragment hasn't received delta.
        }

        mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.MONTH, monthDelta);

        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);

        MonthDayMainPresenterImpl monthDayMainPresenter = new MonthDayMainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new MonthDayRepositoryImpl(),
                this
        );

        monthDayMainPresenter.getItemList(year, month + 1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_month_days_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNestedScrollView = view.findViewById(R.id.nestedScrollView);
        mRecyclerView = view.findViewById(R.id.monthDaysRecyclerView);

        initRecycler();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {

            SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
            sharedViewModel.editedMonthDay.observe(this, new Observer<Pair<MonthDay, Integer>>() {
                @Override
                public void onChanged(@Nullable Pair<MonthDay, Integer> monthDayIntegerPair) {
                    // for easy binding within onBindViewHolder
                    if (mMonthDays.size() == 0) {
                        for (int i = 0; i <= mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                            mMonthDays.add(new MonthDay(
                                    Utils.buildMonthDayID(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH) + 1, i + 1),
                                    new Cell(""), ""));
                        }
                    }

                    if (monthDayIntegerPair != null) {
                        mMonthDays.set(monthDayIntegerPair.getRight(), monthDayIntegerPair.getLeft());
                        mMonthDaysRecyclerAdapter.notifyItemChanged(monthDayIntegerPair.getRight());
                    }
                }
            });
        }
    }

    @Override
    public void showItemList(List<MonthDay> monthDays) {
        mMonthDays.addAll(monthDays);
        mMonthDaysRecyclerAdapter.notifyDataSetChanged();

        //TODO: make sure there won't be an error when it's called too early. Make it safe by null check
        //TODO: when this and onResume are called?
        performScrollingToCurrentDay();
    }

    @Override
    public void onClickItem(long id, int position) {
        EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment =
                EditMonthDayBottomDialogFragment.newInstance((int) id, position);
        editMonthDayBottomDialogFragment.show(getChildFragmentManager(), this.getClass().getSimpleName());
    }

    @Override
    public void onProgressStarted() {

    }

    @Override
    public void onProgressFinished() {

    }

    @Override
    public void showError(String message) {

    }

    /**
     * This method is called within MyFragmentPagerAdapter.getItem(int position)
     * @param monthDelta !!!
     */
    public static MonthDaysPageFragment newInstance(int monthDelta) {
        MonthDaysPageFragment monthDaysPageFragment = new MonthDaysPageFragment();
        Bundle args = new Bundle();
        args.putInt(MONTH_DELTA_KEY, monthDelta);
        monthDaysPageFragment.setArguments(args);
        return monthDaysPageFragment;
    }

    private void initRecycler() {

        mMonthDays = new ArrayList<>();

        mMonthDaysRecyclerAdapter = new MonthDaysRecyclerAdapter(mMonthDays, mCalendar, this);
        mRecyclerView.setAdapter(mMonthDaysRecyclerAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    private void performScrollingToCurrentDay(){

        int position = mCalendar.get(Calendar.DAY_OF_MONTH) - 1;

        if (position > 3) {
            position -= 3;
        } else {
            position = 0;
        }

        final int finalPosition = position;

        if (Build.VERSION.SDK_INT >= 21) {
            final float y = mRecyclerView.getY() + mRecyclerView.getChildAt(finalPosition).getY();
            mNestedScrollView.smoothScrollTo(0, (int) y);
        } else {
            mRecyclerView.scrollToPosition(finalPosition);
        }
    }
}
