package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormatSymbols;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.EditMonthDayPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.EditMonthDayPresenterImpl;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;

public class EditMonthDayBottomDialogFragment extends BottomSheetDialogFragment implements EditMonthDayPresenter.View {

    private static final String ID_KEY = "monthDayID";

    private EditMonthDayPresenter mEditMonthDayPresenter;

    private int mId;
    private String mAmountString;
    private String mDesc;

    private EditText mDescEditText;
    private EditText mAmountEditText;

    public static EditMonthDayBottomDialogFragment newInstance(final int id) {
        EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment = new EditMonthDayBottomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        editMonthDayBottomDialogFragment.setArguments(args);
        return editMonthDayBottomDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEditMonthDayPresenter = new EditMonthDayPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new MonthDayRepositoryImpl()
        );

        this.mId = getArguments().getInt(ID_KEY, -1);

        if (this.mId == -1){
            //id was not sent
        }

        // first get the old month day data from the DB
        mEditMonthDayPresenter.getMonthDayById(this.mId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);

        mDescEditText = viewRoot.findViewById(R.id.editDescription);
        mAmountEditText = viewRoot.findViewById(R.id.editAmount);
        TextView monthDayTitle = viewRoot.findViewById(R.id.monthDayTitleTextView);
        Button save = viewRoot.findViewById(R.id.saveMonthDayButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditMonthDayPresenter.editMonthDay(
                        mId,
                        mDescEditText.getText().toString(),
                        mAmountEditText.getText().toString()
                );
                dismiss();

            }
        });

        String idString = String.valueOf(mId);
        String monthDay = idString.substring(6, 8);
        int month = Integer.parseInt(idString.substring(4, 6)); // because it's put to DateFormatSymbols().getMonths() below
        monthDayTitle.setText(monthDay + " " + new DateFormatSymbols().getMonths()[month - 1]);

        return viewRoot;
    }

    @Override
    public void onMonthDayRetrieved(@NonNull MonthDay monthDay) {

        mDesc = monthDay.getDescription();
        mAmountString = monthDay.getAmountString();

        mDescEditText.setText(mDesc);
        mAmountEditText.setText(mAmountString);
    }

    @Override
    public void onMonthDayUpdated(MonthDay monthDay) {

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
