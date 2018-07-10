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
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.MainPresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.adapters.MonthDaysRecyclerAdapter;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;
import ru.is88.dailybudgeting.utils.Utils;

public class PageFragment extends Fragment implements MainPresenter.View, EditMonthDayBottomDialogFragment.OnEditingFinishedListener {

    private static final String MONTH_DELTA_KEY = "month_delta_key";

    private Calendar calendar;

    private List<MonthDay> monthDays;
    private MonthDaysRecyclerAdapter monthDaysRecyclerAdapter;
    private RecyclerView recyclerView;

    private MainPresenter mainPresenter;

    private Toolbar toolbar;
    private ImageButton overflowMenuImageButton;
    private NestedScrollView nestedScrollView;

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

        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthDelta);

        mainPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new MonthDayRepositoryImpl()
        );
    }

    @Override
    public void onResume() {
        super.onResume();

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        mainPresenter.getMonthDayList(month + 1, year);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_page, container, false);

        toolbar = viewRoot.findViewById(R.id.monthToolbar);
        overflowMenuImageButton = viewRoot.findViewById(R.id.overflowMenuImageButton);
        initToolbar();

        nestedScrollView = viewRoot.findViewById(R.id.nestedScrollView);
        recyclerView = viewRoot.findViewById(R.id.monthDaysRecyclerView);
        initRecycler();

        return viewRoot;
    }

    /**
     * This method is called within MyFragmentPagerAdapter.getItem(int position)
     * @param monthDelta !!!
     */
    public static PageFragment newInstance(int monthDelta) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MONTH_DELTA_KEY, monthDelta);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void showMonthDays(List<MonthDay> monthDays) {
        //this.monthDays.clear();
        this.monthDays.addAll(monthDays);
        monthDaysRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickEditMonthDay(final int monthDayId, final int position) {

        final ViewPager viewPager = Objects.requireNonNull(getActivity(),
                this.getClass().getSimpleName() + " got null getActivity() or findViewById(R.id.viewPager)")
                .findViewById(R.id.viewPager);

        final EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment =
                EditMonthDayBottomDialogFragment.newInstance(monthDayId, position, viewPager.getCurrentItem());
        editMonthDayBottomDialogFragment.show(Objects.requireNonNull(getFragmentManager(),
                this.getClass().getSimpleName() + " got null getFragmentManager()")
                , "edit_month_day_bottom_dialog_fragment");
    }

    @Override
    public void onEditingFinished(final MonthDay monthDay, final int position) {
        this.monthDays.set(position, monthDay);
        monthDaysRecyclerAdapter.notifyItemChanged(position);
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

        monthDays = new ArrayList<>();

        monthDaysRecyclerAdapter = new MonthDaysRecyclerAdapter(this, calendar, monthDays);
        recyclerView.setAdapter(monthDaysRecyclerAdapter);
        recyclerView.setHasFixedSize(true);

        performScrollingToCurrentDay();
    }

    private void performScrollingToCurrentDay(){

        // calendarDayOfMonth is the same as the real day of month, NOT 0-indexed
        int position = calendar.get(Calendar.DAY_OF_MONTH) - 1;

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
                        final float y = recyclerView.getY() + recyclerView.getChildAt(finalPosition).getY();
                        nestedScrollView.smoothScrollTo(0, (int) y);
                    }
                }, 100);
            } catch (Exception e) {
                // Sometimes this method got called faster than recyclerView.getChildAt(finalPosition).getY()
                e.printStackTrace();
            }

        } else {
            recyclerView.scrollToPosition(position);
        }
    }

    private void initToolbar() {

        String title = getMonthNameForTheCurrentFragment() + " " + calendar.get(Calendar.YEAR);
        toolbar.setTitle(title);

        overflowMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Utils.LOG_TAG, "three dots button clicked");
            }
        });
    }

    private String getMonthNameForTheCurrentFragment() {
        return DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_MONTH_DAY | DateUtils.FORMAT_NO_YEAR);
    }
}
