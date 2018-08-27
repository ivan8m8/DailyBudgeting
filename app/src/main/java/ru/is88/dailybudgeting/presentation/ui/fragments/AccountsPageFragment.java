package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.domain.models.accounts.Income;
import ru.is88.dailybudgeting.presentation.ui.adapters.AccountsRecyclerAdapter;

public class AccountsPageFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private RecyclerView recyclerView;
    private AccountsRecyclerAdapter accountsRecyclerAdapter;
    private List<AbstractAccount> accounts;

    private boolean income = false;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AccountsPageFragment newInstance(int sectionNumber) {
        AccountsPageFragment fragment = new AccountsPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                income = true;
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                income = false;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_accounts, container, false);

        recyclerView = viewRoot.findViewById(R.id.accountsRecyclerView);
        initRecycler();

        return viewRoot;
    }

    private void initRecycler() {
        accounts = new ArrayList<>();
        if (income) {
            initIncomeRecycler();
        } else {
            initExpensesRecycler();
        }
    }

    private void initIncomeRecycler(){
        accounts.add(new Income("desc", 1000, 2018, 8));
        accountsRecyclerAdapter = new AccountsRecyclerAdapter(accounts);
        recyclerView.setAdapter(accountsRecyclerAdapter);
    }

    private void initExpensesRecycler(){
        accounts.add(new FixedExpense("desc2", 100, 2018, 8));
        accountsRecyclerAdapter = new AccountsRecyclerAdapter(accounts);
        recyclerView.setAdapter(accountsRecyclerAdapter);
    }
}
