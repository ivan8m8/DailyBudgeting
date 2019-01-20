package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.AddItemPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.AddIncomePresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.Listeners;
import ru.is88.dailybudgeting.storage.IncomeRepositoryImpl;
import ru.is88.dailybudgeting.utils.Utils;

public class AddIncomeDialogFragment
        extends AppCompatDialogFragment
        implements AddItemPresenter.View<Income> {

    private AddIncomePresenterImpl mAddIncomePresenter;

    private int mYear;
    private int mMonth;

    private Listeners.OnIncomeAdded mCallback;

    // requires an empty constructor
    public AddIncomeDialogFragment() {
    }

    public static AddIncomeDialogFragment newInstance(int year, int month) {
        AddIncomeDialogFragment addIncomeDialogFragment = new AddIncomeDialogFragment();
        Bundle args = new Bundle();
        args.putInt(Utils.YEAR_KEY, year);
        args.putInt(Utils.MONTH_KEY, month);
        addIncomeDialogFragment.setArguments(args);
        return addIncomeDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (Listeners.OnIncomeAdded) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass().getName() + " must implement OnIncomeAddedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AddIncomeDialog);

        if (getArguments() != null) {
            mYear = getArguments().getInt(Utils.YEAR_KEY, Utils.DEFAULT_VALUE);
            mMonth = getArguments().getInt(Utils.MONTH_KEY, Utils.DEFAULT_VALUE);
        }

        mAddIncomePresenter = new AddIncomePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new IncomeRepositoryImpl(),
                this
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_add_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleTextView = view.findViewById(R.id.addAccountTitle);
        final TextInputEditText descEditText = view.findViewById(R.id.addAccountDescriptionEditText);
        final TextInputEditText amountEditText = view.findViewById(R.id.addAccountAmountEditText);
        final TextInputLayout amountInputLayout = view.findViewById(R.id.accountAmountInputLayout);
        Button cancelButton = view.findViewById(R.id.cancelAddingAccountButton);
        Button addButton = view.findViewById(R.id.addAccountButton);

        titleTextView.setText(R.string.adding_income);

        descEditText.requestFocus();
        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountEditText.getText() != null) {
                    if (!amountEditText.getText().toString().matches(Utils.AMOUNT_REGEX)) {
                        amountInputLayout.setError(getString(R.string.error_3_amount_input));
                    } else {
                        if (descEditText.getText() != null) {
                            mAddIncomePresenter.addNewAccount(
                                    mYear,
                                    mMonth,
                                    new Cell(amountEditText.getText().toString()),
                                    descEditText.getText().toString()
                            );
                            dismiss();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemAdded(Income income) {
        mCallback.onIncomeAdded(income);
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
