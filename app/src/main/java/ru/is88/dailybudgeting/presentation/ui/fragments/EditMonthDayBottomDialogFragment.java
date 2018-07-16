package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Objects;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.EditMonthDayPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.EditMonthDayPresenterImpl;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;
import ru.is88.dailybudgeting.utils.ExpendituresKeyboardCustomView;

public class EditMonthDayBottomDialogFragment extends BottomSheetDialogFragment implements EditMonthDayPresenter.View {

    private static final String REGEX = "[0-9 ]+";

    public interface OnEditingFinishedListener {
        void onEditingFinished(MonthDay monthDay, int position);
    }

    private static final String ID_KEY = "month_day_ID_key";
    private static final String POSITION_KEY = "position_key";
    private static final String FRAGMENT_POSITION_KEY = "fragment_position_key";

    private static final int DEFAULT_VALUE = -1000;

    private EditMonthDayPresenter editMonthDayPresenter;

    private int id;
    private int position;
    private int fragmentPosition;

    private EditText descriptionEditText;
    private EditText amountEditText;

    private OnEditingFinishedListener callback;

    public static EditMonthDayBottomDialogFragment newInstance(final int id, final int position, final int fragmentPosition) {
        EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment = new EditMonthDayBottomDialogFragment();
        Bundle args = new Bundle();

        args.putInt(ID_KEY, id);
        args.putInt(POSITION_KEY, position);
        args.putInt(FRAGMENT_POSITION_KEY, fragmentPosition);

        editMonthDayBottomDialogFragment.setArguments(args);
        return editMonthDayBottomDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.fragmentPosition = Objects.requireNonNull(getArguments(),
                this.getClass().getSimpleName() + " got null getArguments() or getInt()")
                .getInt(FRAGMENT_POSITION_KEY, DEFAULT_VALUE);

        try {
            callback = (OnEditingFinishedListener) Objects.requireNonNull(getActivity(),
                    this.getClass().getSimpleName() + " got null getActivity() or getSupportFragmentManager()")
                    .getSupportFragmentManager()
                    .findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + fragmentPosition);
        } catch (ClassCastException e) {
            throw new ClassCastException(callback.getClass().getName() + " must implement OnEditingFinishedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.id = Objects.requireNonNull(getArguments(),
                this.getClass().getSimpleName() + " got null getArguments() or getInt()")
                .getInt(ID_KEY, DEFAULT_VALUE);
        this.position = getArguments().getInt(POSITION_KEY, DEFAULT_VALUE);

        if (this.id == DEFAULT_VALUE || this.position == DEFAULT_VALUE || this.fragmentPosition == DEFAULT_VALUE){
            //id was not sent
        }

        editMonthDayPresenter = new EditMonthDayPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new MonthDayRepositoryImpl()
        );

        // first get the old month day data from the DB
        editMonthDayPresenter.getMonthDayById(id);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);

        descriptionEditText = viewRoot.findViewById(R.id.editDescription);
        amountEditText = viewRoot.findViewById(R.id.editAmount);
        TextView monthDayTitle = viewRoot.findViewById(R.id.monthDayTitleTextView);
        Button save = viewRoot.findViewById(R.id.saveMonthDayButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amountEditText.getText().toString().matches(REGEX)) {

                    editMonthDayPresenter.editMonthDay(
                            id,
                            descriptionEditText.getText().toString(),
                            amountEditText.getText().toString()
                    );
                    dismiss();
                } else {
                    Log.d("KSI", "NOO");
                }

            }
        });

        String idString = String.valueOf(id);
        String monthDay = idString.substring(6, 8);
        int month = Integer.parseInt(idString.substring(4, 6)); // because it's put to DateFormatSymbols().getMonths() below
        monthDayTitle.setText(monthDay + " " + new DateFormatSymbols().getMonths()[month - 1]);

        return viewRoot;
    }

    @Override
    public void onMonthDayRetrieved(@NonNull MonthDay monthDay) {

        String description = monthDay.getDescription();
        String amountString = monthDay.getAmountString();

        descriptionEditText.setText(description);
        amountEditText.setText(amountString);
    }

    @Override
    public void onMonthDayUpdated(final MonthDay monthDay) {
        callback.onEditingFinished(monthDay, position);
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
