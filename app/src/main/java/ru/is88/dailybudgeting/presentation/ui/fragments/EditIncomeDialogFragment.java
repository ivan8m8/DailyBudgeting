package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.Cell;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.EditItemPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.EditIncomePresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.viewmodels.SharedViewModel;
import ru.is88.dailybudgeting.storage.IncomeRepositoryImpl;
import ru.is88.dailybudgeting.utils.Utils;

public class EditIncomeDialogFragment
        extends AppCompatDialogFragment
        implements EditItemPresenter.View<Income> {

    private EditIncomePresenterImpl mEditIncomePresenter;

    private long mId;
    private int mPosition;

    private SharedViewModel mSharedViewModel;

    private EditText mDescEditText;
    private EditText mAmountEditText;

    public EditIncomeDialogFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.EditIncomeDialog);

        if (getArguments() != null) {
            mId = getArguments().getLong(Utils.ID_KEY, Utils.DEFAULT_VALUE);
            mPosition = getArguments().getInt(Utils.POSITION_KEY, Utils.DEFAULT_VALUE);
        }

        //noinspection StatementWithEmptyBody
        if (mId == Utils.DEFAULT_VALUE || mPosition == Utils.DEFAULT_VALUE) {
            //TODO
        }

        mEditIncomePresenter = new EditIncomePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new IncomeRepositoryImpl(),
                this
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_edit_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDescEditText = view.findViewById(R.id.editAccountDescriptionEditText);
        mAmountEditText = view.findViewById(R.id.editAccountAmountEditText);

        // It's placed here, not within onCreate, because
        // there is a theoretical opportunity the presenter's callback onItemRetrieved
        // could be called before mDescEditText & mAmountEditText are initialized.
        mEditIncomePresenter.getAccountById(mId);

        TextView title = view.findViewById(R.id.editAccountTitle);
        final TextInputLayout amountInputLayout = view.findViewById(R.id.editAccountAmountInputLayout);
        final Button edit = view.findViewById(R.id.editAccountButton);
        Button cancel = view.findViewById(R.id.cancelEditingAccountButton);

        title.setText(R.string.edit_income);

        mDescEditText.requestFocus();
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAmountEditText.getText() != null) {
                    if (!mAmountEditText.getText().toString().matches(Utils.AMOUNT_REGEX))
                        amountInputLayout.setError(getString(R.string.error_3_amount_input));
                    else {
                        if (mDescEditText.getText() != null) {
                            mEditIncomePresenter.editAccount(
                                    mId,
                                    new Cell(mAmountEditText.getText().toString()),
                                    mDescEditText.getText().toString()
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

        if (getActivity() != null)
            mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Override
    public void onItemRetrieved(Income item) {
        mDescEditText.setText(item.getDescription());
        mAmountEditText.setText(item.getAmountCell().getValue());
    }

    @Override
    public void onItemUpdated(Income item) {
        mSharedViewModel.setEditedIncome(item, mPosition);
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

    public static EditIncomeDialogFragment newInstance(long id, int position) {
        EditIncomeDialogFragment f = new EditIncomeDialogFragment();
        Bundle args = new Bundle();
        args.putLong(Utils.ID_KEY, id);
        args.putInt(Utils.POSITION_KEY, position);
        f.setArguments(args);
        return f;
    }
}
