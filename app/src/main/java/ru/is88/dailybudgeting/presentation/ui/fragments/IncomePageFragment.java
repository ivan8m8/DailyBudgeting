package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.is88.dailybudgeting.MainThreadImpl;
import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.IncomeMainPresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.adapters.AccountsRecyclerAdapter;
import ru.is88.dailybudgeting.storage.IncomeRepositoryImpl;
import ru.is88.dailybudgeting.utils.Utils;

public class IncomePageFragment extends Fragment implements MainPresenter.View<Income> {

    private int mYear;
    private int mMonth;

    private IncomeMainPresenterImpl mIncomeMainPresenter;

    private RecyclerView mRecyclerView;
    private AccountsRecyclerAdapter mIncomeRecyclerAdapter;
    private List<AbstractAccount> mAccounts;

    public static IncomePageFragment newInstance(int year, int month) {
        IncomePageFragment incomePageFragment = new IncomePageFragment();
        Bundle args = new Bundle();
        args.putInt(Utils.YEAR_KEY, year);
        args.putInt(Utils.MONTH_KEY, month);
        incomePageFragment.setArguments(args);
        return incomePageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mYear = getArguments().getInt(Utils.YEAR_KEY, Utils.DEFAULT_VALUE);
            mMonth = getArguments().getInt(Utils.MONTH_KEY, Utils.DEFAULT_VALUE);
        }

        mIncomeMainPresenter = new IncomeMainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new IncomeRepositoryImpl(),
                this
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accounts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.accountsRecyclerView);
        initRecycler();
    }

    @Override
    public void onResume() {
        super.onResume();

        mIncomeMainPresenter.getItemList(mYear, mMonth);
    }

    @Override
    public void showItemList(List<Income> incomes) {
        mAccounts.clear();
        mAccounts.addAll(incomes);
        mIncomeRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItem(long id, int position) {

    }

    @Override
    public void onProgressStarted() {

    }

    @Override
    public void onProgressFinished() {

    }

    @Override
    public void showError(String message) {

    }

    public void notifyAdapterItemInserted(Income income) {
        mAccounts.add(income);
        mIncomeRecyclerAdapter.notifyItemInserted(mIncomeRecyclerAdapter.getItemCount());
    }

    private void initRecycler() {
        mAccounts = new ArrayList<>();
        mIncomeRecyclerAdapter = new AccountsRecyclerAdapter(mAccounts);
        mRecyclerView.setAdapter(mIncomeRecyclerAdapter);
        mRecyclerView.setHasFixedSize(true);
    }
}
