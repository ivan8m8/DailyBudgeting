package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormatSymbols;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.utils.Utils;

public class EditMonthDayBottomDialogFragment extends BottomSheetDialogFragment {

    private static final String ID_KEY = "monthDayID";

    private int id;

    public static EditMonthDayBottomDialogFragment newInstance(int id) {
        EditMonthDayBottomDialogFragment editMonthDayBottomDialogFragment = new EditMonthDayBottomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        editMonthDayBottomDialogFragment.setArguments(args);
        return editMonthDayBottomDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.id = getArguments().getInt(ID_KEY, -1);

        View viewRoot = inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);

        TextView monthDayTitle = viewRoot.findViewById(R.id.monthDayTitleTextView);

        String idString = String.valueOf(id);
        String monthDay = idString.substring(6, 8);
        int month = Integer.parseInt(idString.substring(4, 6)); // because it's put to DateFormatSymbols().getMonths() below
        monthDayTitle.setText(monthDay + " " + new DateFormatSymbols().getMonths()[month - 1]);

        return viewRoot;
    }
}
