package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.ui.listeners.MonthDaysRecyclerViewListener;
import ru.is88.dailybudgeting.utils.Utils;

public class MonthDaysRecyclerAdapter extends RecyclerView.Adapter<MonthDaysRecyclerAdapter.ViewHolder>
        implements MonthDaysRecyclerViewListener{

    private List<MonthDay> monthDays;
    private Calendar calendar;
    private DateFormatSymbols dateFormatSymbols;

    public final MainPresenter.View view;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MonthDaysRecyclerViewListener monthDaysRecyclerViewListener;

        private TextView dateTextView;
        private TextView descTextView;
        private TextView saldoTextView;
        private TextView amountTextView;

        ViewHolder(View itemView, final MonthDaysRecyclerViewListener monthDaysRecyclerViewListener) {
            super(itemView);

            this.monthDaysRecyclerViewListener = monthDaysRecyclerViewListener;
            itemView.setOnClickListener(this);

            dateTextView = itemView.findViewById(R.id.dateTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            saldoTextView = itemView.findViewById(R.id.saldoTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }

        @Override
        public void onClick(View v) {
            monthDaysRecyclerViewListener.onClickView(getAdapterPosition());
        }
    }

    public MonthDaysRecyclerAdapter(MainPresenter.View view,
                                    Calendar calendar,
                                    List<MonthDay> monthDays) {
        this.view = view;
        this.calendar = calendar;
        this.monthDays = monthDays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_month_day, parent, false);
        dateFormatSymbols = new DateFormatSymbols();
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final int day = holder.getAdapterPosition() + 1;

        String dateString = String.valueOf(day)
                + " "
                + dateFormatSymbols.getMonths()[calendar.get(Calendar.MONTH)];
        holder.dateTextView.setText(dateString);

        if (monthDays.size() != 0) {
            holder.descTextView.setText(monthDays.get(holder.getAdapterPosition()).getDescription());
            holder.amountTextView.setText(String.valueOf(monthDays.get(holder.getAdapterPosition()).getAmount()));
        }
    }

    @Override
    public int getItemCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClickView(final int position) {
        view.onClickEditMonthDay(Utils.buildMonthDayID(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                position + 1),
                position);
    }
}
