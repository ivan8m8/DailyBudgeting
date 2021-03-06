package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import ru.is88.dailybudgeting.R;

public class AddFixedExpenseDialogFragment extends AppCompatDialogFragment {

    public AddFixedExpenseDialogFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AddFixedExpenseDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_add_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView title = view.findViewById(R.id.addAccountTitle);
        title.setText(R.string.adding_fixed_expense);

        TextInputEditText descEditText = view.findViewById(R.id.addAccountDescriptionEditText);

        //TODO: what does it do? MOVE IT TO RESUME?
        descEditText.requestFocus();

        if (getDialog().getWindow() != null)
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        //TODO end
    }

    public static AddFixedExpenseDialogFragment newInstance(int year, int month) {
        return new AddFixedExpenseDialogFragment();
    }
}
