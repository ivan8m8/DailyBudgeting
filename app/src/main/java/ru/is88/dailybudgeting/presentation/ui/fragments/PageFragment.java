package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Calendar;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.ui.adapters.MonthDaysRecyclerAdapter;
import ru.is88.dailybudgeting.utils.Utils;

public class PageFragment extends Fragment implements MainPresenter.View {

    private static final String DELTA_KEY = "delta_key";

    private int mDelta;

    private Calendar calendar;
    private MainPresenter mainPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDelta = getArguments().getInt(DELTA_KEY);
        } else {
            mDelta = 0;
            // TODO: handle a mistake. The PageFragment hasn't received delta.
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_page, container, false);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, mDelta);

        Toolbar toolbar = viewRoot.findViewById(R.id.monthToolbar);
        String title = getMonthNameForTheCurrentFragment() + " " + calendar.get(Calendar.YEAR);
        toolbar.setTitle(title);

        // check layouts, someone overlays the button so it cannot be tapped
        ImageButton overflowMenuImageButton = viewRoot.findViewById(R.id.overflowMenuImageButton);
        overflowMenuImageButton.setClickable(true);
        overflowMenuImageButton.setEnabled(true);
        overflowMenuImageButton.setActivated(true);
        overflowMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Utils.LOG_TAG, "clicked");
            }
        });

        boolean bb = overflowMenuImageButton.isClickable() && overflowMenuImageButton.isActivated() && overflowMenuImageButton.isEnabled();
        Log.d(Utils.LOG_TAG, String.valueOf(bb));

        RecyclerView recyclerView = viewRoot.findViewById(R.id.monthDaysRecyclerView);
        MonthDaysRecyclerAdapter monthDaysRecyclerAdapter = new MonthDaysRecyclerAdapter(this, calendar);
        recyclerView.setAdapter(monthDaysRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.scrollToPosition(calendar.get(Calendar.DAY_OF_MONTH));

        return viewRoot;
    }

    private String getMonthNameForTheCurrentFragment() {
        return DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_MONTH_DAY | DateUtils.FORMAT_NO_YEAR);
    }

    /**
     * This method is called within MyFragmentPagerAdapter.getItem(int position)
     * @param delta !!!
     */
    public static PageFragment newInstance(int delta) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DELTA_KEY, delta);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onClickEditMonthDay(int monthDayId) {
        EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment = EditMonthDayBottomDialogFragment.newInstance(monthDayId);
        editMonthDayBottomDialogFragment.show(getFragmentManager(), "edit_month_day_bottom_dialog_fragment");
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
}
