package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
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

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.AddItemPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.AddIncomePresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.viewmodels.SharedViewModel;
import ru.is88.dailybudgeting.storage.IncomeRepositoryImpl;
import ru.is88.dailybudgeting.Utils;

public class AddIncomeDialogFragment
        extends AppCompatDialogFragment
        implements AddItemPresenter.View<Income> {

    private AddIncomePresenterImpl mAddIncomePresenter;

    private int mYear;
    private int mMonth;

    private SharedViewModel mSharedViewModel;

    // requires an empty constructor
    public AddIncomeDialogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AddIncomeDialog); // FIXME: 21.01.2019 move to onActivityCreated()
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

        if (getDialog().getWindow() != null)
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

        if (getActivity() != null)
            mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Override
    public void onItemAdded(Income item) {
        mSharedViewModel.setAddedIncome(item);
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

    public static AddIncomeDialogFragment newInstance(int year, int month) {
        AddIncomeDialogFragment addIncomeDialogFragment = new AddIncomeDialogFragment();
        Bundle args = new Bundle();
        args.putInt(Utils.YEAR_KEY, year);
        args.putInt(Utils.MONTH_KEY, month);
        addIncomeDialogFragment.setArguments(args);
        return addIncomeDialogFragment;
    }
}
