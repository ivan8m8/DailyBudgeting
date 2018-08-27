package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.ViewHolder> {

    private List<AbstractAccount> accounts;

    public AccountsRecyclerAdapter(List<AbstractAccount> accounts) {
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.desc.setText(accounts.get(holder.getAdapterPosition()).getDescription());
        holder.amount.setText(String.valueOf(accounts.get(holder.getAdapterPosition()).getAmount()));
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView desc;
        private TextView amount;
        private ImageButton delete;

        public ViewHolder(View itemView) {
            super(itemView);

            desc = itemView.findViewById(R.id.accountDescTextView);
            amount = itemView.findViewById(R.id.accountAmountTextView);
            delete = itemView.findViewById(R.id.deleteAccountImageButton);
        }

        @Override
        public void onClick(View v) {
            // TODO: fix!
            Log.d("KSI", "cl");
        }
    }
}
