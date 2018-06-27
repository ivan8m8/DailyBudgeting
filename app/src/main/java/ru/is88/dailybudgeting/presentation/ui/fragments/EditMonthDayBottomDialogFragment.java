package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.is88.dailybudgeting.R;

public class EditMonthDayBottomDialogFragment extends BottomSheetDialogFragment {

    public static EditMonthDayBottomDialogFragment newInstance() {
        return new EditMonthDayBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);
        return view;
    }
}
