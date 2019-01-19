package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.MonthDayMainPresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.adapters.MonthDaysRecyclerAdapter;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;
import ru.is88.dailybudgeting.utils.Utils;

public class MonthDaysPageFragment extends Fragment
        implements MainPresenter.View<MonthDay>, EditMonthDayBottomDialogFragment.OnEditingFinishedListener {

    private static final String MONTH_DELTA_KEY = "month_delta_key";

    private Calendar mCalendar;

    private List<MonthDay> mMonthDays;
    private MonthDaysRecyclerAdapter mMonthDaysRecyclerAdapter;
    private RecyclerView mRecyclerView;

    private NestedScrollView mNestedScrollView;

    private MonthDayMainPresenterImpl mMonthDayMainPresenter;

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

        mMonthDayMainPresenter = new MonthDayMainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new MonthDayRepositoryImpl(),
                this
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_month_days_page, container, false);

        mNestedScrollView = viewRoot.findViewById(R.id.nestedScrollView);
        mRecyclerView = viewRoot.findViewById(R.id.monthDaysRecyclerView);
        initRecycler();

        return viewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();

        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);
        mMonthDayMainPresenter.getItemList(year, month + 1);
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

    @Override
    public void showItemList(List<MonthDay> monthDays) {
        //mMonthDays.clear();
        mMonthDays.addAll(monthDays);
        mMonthDaysRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItem(long id, int position) {
        final ViewPager viewPager = Objects.requireNonNull(getActivity(),
                this.getClass().getSimpleName() + " got null getActivity() or findViewById(R.id.viewPager)")
                .findViewById(R.id.monthDaysViewPager);

        final EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment =
                EditMonthDayBottomDialogFragment.newInstance((int) id, position, viewPager.getCurrentItem());
        editMonthDayBottomDialogFragment.show(Objects.requireNonNull(getFragmentManager(),
                this.getClass().getSimpleName() + " got null getFragmentManager()")
                , "edit_month_day_bottom_dialog_fragment");
    }

    @Override
    public void onEditingFinished(MonthDay monthDay, int position) {

        if (mMonthDays.size() == 0) {
            for (int i = 0; i <= mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                mMonthDays.add(new MonthDay(
                        Utils.buildMonthDayID(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH) + 1, i + 1),
                        new Cell(""), ""));
            }
        }

        mMonthDays.set(position, monthDay);
        mMonthDaysRecyclerAdapter.notifyItemChanged(position);
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

    private void initRecycler() {

        mMonthDays = new ArrayList<>();

        mMonthDaysRecyclerAdapter = new MonthDaysRecyclerAdapter(mMonthDays, mCalendar, this);
        mRecyclerView.setAdapter(mMonthDaysRecyclerAdapter);
        mRecyclerView.setHasFixedSize(true);

        performScrollingToCurrentDay();
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

            // Don't know why though this doesn't work without a Handler
            // even if the delayMillis equals 1
            try {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final float y = mRecyclerView.getY() + mRecyclerView.getChildAt(finalPosition).getY();
                        mNestedScrollView.smoothScrollTo(0, (int) y);
                    }
                }, 100);
            } catch (Exception e) {
                // Sometimes this method got called faster than mRecyclerView.getChildAt(finalPosition).getY()
                e.printStackTrace();
            }

        } else {
            mRecyclerView.scrollToPosition(position);
        }
    }
}
