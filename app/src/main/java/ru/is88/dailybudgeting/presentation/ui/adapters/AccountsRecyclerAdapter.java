package ru.is88.dailybudgeting.presentation.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.ui.Listeners;

public class AccountsRecyclerAdapter
        extends RecyclerView.Adapter<AccountsRecyclerAdapter.ViewHolder>
        implements Listeners.OnRecyclerViewItemClick {

    private List<AbstractAccount> mAccounts;

    private final MainPresenter.View<? extends AbstractAccount> mView;

    public AccountsRecyclerAdapter(List<AbstractAccount> accounts,
                                   MainPresenter.View<? extends AbstractAccount> view) {
        mAccounts = accounts;
        mView = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.desc.setText(mAccounts.get(holder.getAdapterPosition()).getDescription());
        holder.amount.setText(String.valueOf(mAccounts.get(holder.getAdapterPosition()).getAmountCell().getDouble()));
    }

    @Override
    public int getItemCount() {
        return mAccounts.size();
    }

    @Override
    public void onClick(int position) {
        mView.onClickItem(mAccounts.get(position).getId(), position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView desc;
        private TextView amount;
        private ImageButton delete;
        private View accountInfoLayoutClickable;

        private Listeners.OnRecyclerViewItemClick onRecyclerViewItemClickListener;

        ViewHolder(View itemView, final Listeners.OnRecyclerViewItemClick onRecyclerViewItemClickListener) {
            super(itemView);

            desc = itemView.findViewById(R.id.accountDescTextView);
            amount = itemView.findViewById(R.id.accountAmountTextView);
            delete = itemView.findViewById(R.id.deleteAccountImageButton);
            accountInfoLayoutClickable = itemView.findViewById(R.id.accountInfoClickableLayout);

            accountInfoLayoutClickable.setOnClickListener(this);
            this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onRecyclerViewItemClickListener.onClick(getAdapterPosition());
        }
    }
}
