package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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
import ru.is88.dailybudgeting.presentation.ui.Listeners;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;
import ru.is88.dailybudgeting.Utils;

public class EditMonthDayBottomDialogFragment extends BottomSheetDialogFragment implements EditItemPresenter.View<MonthDay> {

    private static final String ID_KEY = "month_day_ID_key";
    private static final String POSITION_KEY = "position_key";
    private static final String FRAGMENT_POSITION_KEY = "fragment_position_key";

    private EditMonthDayPresenter mEditMonthDayPresenter;

    private int mId;
    private int mPosition;
    private int mFragmentPosition; // is actually the month

    private EditText mDescriptionEditText;
    private EditText mAmountEditText;

    private Listeners.OnMonthDayEdited mCallback;

    private boolean mInputErrorOccurred = false;

    public static EditMonthDayBottomDialogFragment newInstance(int id, int position, int fragmentPosition) {

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

        if (getArguments() != null)
            mFragmentPosition = getArguments().getInt(FRAGMENT_POSITION_KEY, Utils.DEFAULT_VALUE);

        if (getActivity() != null) {
            try {
                mCallback = (Listeners.OnMonthDayEdited) getActivity().getSupportFragmentManager()
                        .findFragmentByTag("android:switcher:" + R.id.monthDaysViewPager + ":" + mFragmentPosition);
            } catch (ClassCastException e) {
                throw new ClassCastException(mCallback.getClass().getName() + " must implement OnEditingFinishedListener");
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mId = getArguments().getInt(ID_KEY, Utils.DEFAULT_VALUE);
            mPosition = getArguments().getInt(POSITION_KEY, Utils.DEFAULT_VALUE);
        }

        //noinspection StatementWithEmptyBody
        if (mId == Utils.DEFAULT_VALUE || mPosition == Utils.DEFAULT_VALUE || mFragmentPosition == Utils.DEFAULT_VALUE){
            //mId was not sent
        }

        mEditMonthDayPresenter = new EditMonthDayPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new MonthDayRepositoryImpl(),
                this
        );

        // first get the old month day data from the DB
        mEditMonthDayPresenter.getMonthDayById(mId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDescriptionEditText = view.findViewById(R.id.editDescription);
        mAmountEditText = view.findViewById(R.id.editAmount);
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
    public void onItemRetrieved(MonthDay item) {

        String description = item.getDescription();
        String amountString = item.getAmountCell().getValue();

        mDescriptionEditText.setText(description);
        mAmountEditText.setText(amountString);
    }

    @Override
    public void onItemUpdated(MonthDay item) {
        mCallback.onItemEdited(item, mPosition);
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
