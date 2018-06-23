package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.MonthDay;

public class MonthDaysRecyclerAdapter extends RecyclerView.Adapter<MonthDaysRecyclerAdapter.ViewHolder> {

    private ArrayList<MonthDay> monthDays;
    private Calendar calendar;
    private DateFormatSymbols dateFormatSymbols;

    public MonthDaysRecyclerAdapter(Calendar calendar) {
        this.calendar = calendar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_month_day, parent, false);
        dateFormatSymbols = new DateFormatSymbols();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String dateString = String.valueOf(position + 1)
                + " "
                + dateFormatSymbols.getMonths()[calendar.get(Calendar.MONTH)];
        holder.dateTextView.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
