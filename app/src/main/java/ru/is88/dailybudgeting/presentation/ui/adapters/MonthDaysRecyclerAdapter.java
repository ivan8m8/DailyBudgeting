package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.ui.listeners.MonthDaysRecyclerViewListener;
import ru.is88.dailybudgeting.utils.Utils;

public class MonthDaysRecyclerAdapter extends RecyclerView.Adapter<MonthDaysRecyclerAdapter.ViewHolder> implements MonthDaysRecyclerViewListener{

    private ArrayList<MonthDay> monthDays;
    private Calendar calendar;
    private DateFormatSymbols dateFormatSymbols;

    public final MainPresenter.View view;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MonthDaysRecyclerViewListener monthDaysRecyclerViewListener;

        private TextView dateTextView;

        public ViewHolder(View itemView, final MonthDaysRecyclerViewListener monthDaysRecyclerViewListener) {
            super(itemView);

            this.monthDaysRecyclerViewListener = monthDaysRecyclerViewListener;
            itemView.setOnClickListener(this);

            dateTextView = itemView.findViewById(R.id.dateTextView);
        }

        @Override
        public void onClick(View v) {
            monthDaysRecyclerViewListener.onClickView(getAdapterPosition());
        }
    }

    public MonthDaysRecyclerAdapter(MainPresenter.View view, Calendar calendar) {
        this.view = view;
        this.calendar = calendar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_month_day, parent, false);
        dateFormatSymbols = new DateFormatSymbols();
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String dateString = String.valueOf(position + 1)
                + " "
                + dateFormatSymbols.getMonths()[calendar.get(Calendar.MONTH)];
        holder.dateTextView.setText(dateString);

        // here do id and edit
    }

    @Override
    public int getItemCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClickView(int position) {
        String idString;
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = position + 1;
        idString = String.valueOf(calendar.get(Calendar.YEAR)) +
                String.valueOf(month < 10 ? "0" : "") + month +
                String.valueOf(dayOfMonth < 10 ? "0" : "") + dayOfMonth;
        Log.d(Utils.LOG_TAG, "id " + idString);
        view.onClickEditMonthDay(Integer.parseInt(idString));
    }
}
