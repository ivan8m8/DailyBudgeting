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
import java.util.Objects;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.EditItemPresenter;
import ru.is88.dailybudgeting.presentation.presenters.EditMonthDayPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.EditMonthDayPresenterImpl;
import ru.is88.dailybudgeting.storage.MonthDayRepositoryImpl;
import ru.is88.dailybudgeting.utils.Utils;

public class EditMonthDayBottomDialogFragment extends BottomSheetDialogFragment implements EditItemPresenter.View<MonthDay> {

    private static final String REGEX = "^\\d+(.\\d+)?(\\s\\d+(.\\d+)?)*$";

    public interface OnEditingFinishedListener {
        void onEditingFinished(MonthDay monthDay, int position);
    }

    private static final String ID_KEY = "month_day_ID_key";
    private static final String POSITION_KEY = "position_key";
    private static final String FRAGMENT_POSITION_KEY = "fragment_position_key";

    private EditMonthDayPresenter mEditMonthDayPresenter;

    private int mId;
    private int mPosition;
    private int mFragmentPosition; // is actually the month

    private EditText mDescriptionEditText;
    private EditText mAmountEditText;

    private OnEditingFinishedListener mCallback;

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

        mFragmentPosition = Objects.requireNonNull(getArguments(),
                this.getClass().getSimpleName() + " got null getArguments() or getInt()")
                .getInt(FRAGMENT_POSITION_KEY, Utils.DEFAULT_VALUE);

        try {
            mCallback = (OnEditingFinishedListener) Objects.requireNonNull(getActivity(),
                    this.getClass().getSimpleName() + " got null getActivity() or getSupportFragmentManager()")
                    .getSupportFragmentManager()
                    .findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + mFragmentPosition);
        } catch (ClassCastException e) {
            throw new ClassCastException(mCallback.getClass().getName() + " must implement OnEditingFinishedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mId = Objects.requireNonNull(
                getArguments(), this.getClass().getSimpleName() + " got null getArguments() or getInt()")
                .getInt(ID_KEY, Utils.DEFAULT_VALUE);
        mPosition = getArguments().getInt(POSITION_KEY, Utils.DEFAULT_VALUE);

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

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);

        mDescriptionEditText = viewRoot.findViewById(R.id.editDescription);
        mAmountEditText = viewRoot.findViewById(R.id.editAmount);
        TextView monthDayTitle = viewRoot.findViewById(R.id.monthDayTitleTextView);
        Button save = viewRoot.findViewById(R.id.saveMonthDayButton);

        String idString = String.valueOf(mId);
        String monthDayString = idString.substring(6, 8);
        int month = Integer.parseInt(idString.substring(4, 6)); // because it's put to DateFormatSymbols().getMonths() below
        monthDayTitle.setText(monthDayString + " " + new DateFormatSymbols().getMonths()[month - 1]);

        final TextInputLayout amountInputLayout = viewRoot.findViewById(R.id.amountInputLayout);
        mAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mInputErrorOccurred) {
                    if (!mAmountEditText.getText().toString().matches(REGEX)) {
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
                if (!mAmountEditText.getText().toString().matches(REGEX)) {
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

        return viewRoot;
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
        mCallback.onEditingFinished(item, mPosition);
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
