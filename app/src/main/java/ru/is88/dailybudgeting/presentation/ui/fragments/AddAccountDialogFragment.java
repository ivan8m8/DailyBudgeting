package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.is88.dailybudgeting.R;

public class AddAccountDialogFragment extends DialogFragment {

    public AddAccountDialogFragment() {
    }

    public static AddAccountDialogFragment newInstance() {
        return new AddAccountDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_add_account, container, false);
    }
}
