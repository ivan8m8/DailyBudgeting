package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.MonthDay;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.ui.listeners.OnRecyclerViewItemClickListener;
import ru.is88.dailybudgeting.utils.Utils;

public class MonthDaysRecyclerAdapter extends RecyclerView.Adapter<MonthDaysRecyclerAdapter.ViewHolder>
        implements OnRecyclerViewItemClickListener {

    private List<MonthDay> mMonthDays;
    private Calendar mCalendar;
    private DateFormatSymbols mDateFormatSymbols;

    public final MainPresenter.View<MonthDay> mView;

    private NumberFormat mNumberFormat;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

        private TextView dateTextView;
        private TextView descTextView;
        private TextView saldoTextView;
        private TextView amountTextView;

        ViewHolder(View itemView, final OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            super(itemView);

            this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
            itemView.setOnClickListener(this);

            dateTextView = itemView.findViewById(R.id.dateTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            saldoTextView = itemView.findViewById(R.id.saldoTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }

        @Override
        public void onClick(View v) {
            /*
            Should use additional interface that is above, since unable to call
            mView.onClickItem that is from the outer class.
             */
            onRecyclerViewItemClickListener.onClick(getAdapterPosition());
        }
    }

    public MonthDaysRecyclerAdapter(List<MonthDay> monthDays,
                                    Calendar calendar,
                                    MainPresenter.View<MonthDay> view) {
        mMonthDays = monthDays;
        mCalendar = calendar;
        mView = view;

        mNumberFormat = NumberFormat.getInstance();
        mNumberFormat.setMinimumFractionDigits(0);
        mNumberFormat.setGroupingUsed(false); // TODO: to be asked within the preferences
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_month_day, parent, false);

        // TODO: the following should be called within the constructor?
        mDateFormatSymbols = new DateFormatSymbols();

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //TODO: Violation of Single Responsibility

        final int day = holder.getAdapterPosition() + 1;

        String dateString = mDateFormatSymbols.getMonths()[mCalendar.get(Calendar.MONTH)]
                + " "
                + String.valueOf(day);

        // END

        holder.dateTextView.setText(dateString);

        //TODO: Violation of SRP #2
        // mNumberFormat.format should be called within Utils, for example. not in here

        if (mMonthDays.size() != 0) {
            holder.descTextView.setText(mMonthDays.get(holder.getAdapterPosition()).getDescription());
            holder.amountTextView.setText(mNumberFormat.format(mMonthDays.get(holder.getAdapterPosition()).getAmountCell().getDouble()));
        }
    }

    @Override
    public int getItemCount() {
        return mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(int position) {
        mView.onClickItem(Utils.buildMonthDayID(
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH) + 1,
                position + 1),
                position);
    }
}
