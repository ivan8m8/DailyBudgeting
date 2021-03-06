package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
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
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.EditItemPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditMonthDayPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.EditMonthDayPresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.viewmodels.SharedViewModel;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;
import ru.is88.dailybudgeting.utils.Utils;

public class EditMonthDayBottomDialogFragment extends BottomSheetDialogFragment implements EditItemPresenter.View<MonthDay> {

    private int mId;
    private int mPosition;

    private EditMonthDayPresenter mEditMonthDayPresenter;
    private SharedViewModel mSharedViewModel;

    private EditText mDescriptionEditText;
    private EditText mAmountEditText;

    private boolean mInputErrorOccurred = false;

    public EditMonthDayBottomDialogFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mId = getArguments().getInt(Utils.ID_KEY, Utils.DEFAULT_VALUE);
            mPosition = getArguments().getInt(Utils.POSITION_KEY, Utils.DEFAULT_VALUE);
        }

        //noinspection StatementWithEmptyBody
        if (mId == Utils.DEFAULT_VALUE || mPosition == Utils.DEFAULT_VALUE) {
            //TODO
        }

        mEditMonthDayPresenter = new EditMonthDayPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new MonthDayRepositoryImpl(),
                this
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO: on Android 9 it's unable to scroll the bottom fragment to see the add & cancel buttons
        //TODO: requestFocus + getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE); may help
        return inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDescriptionEditText = view.findViewById(R.id.monthDayDescriptionEditText);
        mAmountEditText = view.findViewById(R.id.monthDayAmountEditText);

        // first fetch old month day data from the DB
        mEditMonthDayPresenter.getMonthDayById(mId);

        TextView monthDayTitle = view.findViewById(R.id.monthDayTitleTextView);
        Button save = view.findViewById(R.id.saveMonthDayButton);

        String idString = String.valueOf(mId);
        String monthDayString = idString.substring(6, 8);
        int month = Integer.parseInt(idString.substring(4, 6)); // because it's put to DateFormatSymbols().getMonths() below
        monthDayTitle.setText(monthDayString + " " + new DateFormatSymbols().getMonths()[month - 1]);

        final TextInputLayout amountInputLayout = view.findViewById(R.id.monthDayAmountInputLayout);
        mAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mInputErrorOccurred) {
                    if (!mAmountEditText.getText().toString().matches(Utils.AMOUNT_REGEX)) {
                        amountInputLayout.setError(getString(R.string.error_3_amount_input));
                    } else {
                        amountInputLayout.setErrorEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAmountEditText.getText().toString().matches(Utils.AMOUNT_REGEX)) {
                    mInputErrorOccurred = true;
                    amountInputLayout.setError(getString(R.string.error_3_amount_input));
                } else {
                    mEditMonthDayPresenter.editMonthDay(
                            mId,
                            new Cell(mAmountEditText.getText().toString()),
                            mDescriptionEditText.getText().toString()
                    );
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null)
            mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Override
    public void onItemRetrieved(MonthDay item) {

        String description = item.getDescription();
        String amountString = item.getAmountCell().getValue();

        mDescriptionEditText.setText(description);
        mAmountEditText.setText(amountString);
    }

    @Override
    public void onItemUpdated(MonthDay item) {
        mSharedViewModel.setEditedMonthDay(item, mPosition);
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

    public static EditMonthDayBottomDialogFragment newInstance(int id, int position) {

        EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment = new EditMonthDayBottomDialogFragment();
        Bundle args = new Bundle();

        args.putInt(Utils.ID_KEY, id);
        args.putInt(Utils.POSITION_KEY, position);

        editMonthDayBottomDialogFragment.setArguments(args);
        return editMonthDayBottomDialogFragment;
    }
}
