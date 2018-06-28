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

        Log.d(Utils.LOG_TAG, String.valueOf(id));

        View viewRoot = inflater.inflate(R.layout.fragment_bottom_dialog_edit_month_day, container, false);

        TextView monthDayTitle = viewRoot.findViewById(R.id.monthDayTitleTextView);

        /*
         * As an Integer can never start with leading zero
         */
        String idString = String.valueOf(id);
        String monthDay = "??";
        int month = -1; // because it then puts to Calendar
        if (idString.length() == 7) {
            monthDay = idString.substring(0, 1);
            month = Integer.parseInt(idString.substring(1, 3));
        } else if (idString.length() == 8) {
            monthDay = idString.substring(0, 2);
            month = Integer.parseInt(idString.substring(2, 4));
        }

        Log.d(Utils.LOG_TAG, idString);

        monthDayTitle.setText(monthDay + " " + new DateFormatSymbols().getMonths()[month - 1]);

        return viewRoot;
    }
}
