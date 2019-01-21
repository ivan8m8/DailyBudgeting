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
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.presenters.impl.FixedExpenseMainPresenterImpl;
import ru.is88.dailybudgeting.presentation.ui.adapters.AccountsRecyclerAdapter;
import ru.is88.dailybudgeting.storage.FixedExpenseRepositoryImpl;
import ru.is88.dailybudgeting.Utils;

public class FixedExpensesPageFragment extends Fragment implements MainPresenter.View<FixedExpense> {

    private int mYear;
    private int mMonth;

    private FixedExpenseMainPresenterImpl mFixedExpenseMainPresenter;

    private List<AbstractAccount> mAccounts;
    private RecyclerView mRecyclerView;
    private AccountsRecyclerAdapter mAccountsRecyclerAdapter;

    public static FixedExpensesPageFragment newInstance(int year, int month) {
        FixedExpensesPageFragment fixedExpensesPageFragment = new FixedExpensesPageFragment();
        Bundle args = new Bundle();
        args.putInt(Utils.YEAR_KEY, year);
        args.putInt(Utils.MONTH_KEY, month);
        fixedExpensesPageFragment.setArguments(args);
        return fixedExpensesPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mYear = getArguments().getInt(Utils.YEAR_KEY, Utils.DEFAULT_VALUE);
            mMonth = getArguments().getInt(Utils.MONTH_KEY, Utils.DEFAULT_VALUE);
        }

        mFixedExpenseMainPresenter = new FixedExpenseMainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new FixedExpenseRepositoryImpl(),
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

        mFixedExpenseMainPresenter.getItemList(mYear, mMonth);
    }

    @Override
    public void showItemList(List<FixedExpense> fixedExpenses) {
        mAccounts.clear();
        mAccounts.addAll(fixedExpenses);
        mAccountsRecyclerAdapter.notifyDataSetChanged();
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

    private void initRecycler() {
        mAccounts = new ArrayList<>();
        mAccountsRecyclerAdapter = new AccountsRecyclerAdapter(mAccounts, this);
        mRecyclerView.setAdapter(mAccountsRecyclerAdapter);
        mRecyclerView.setHasFixedSize(true);
    }
}
