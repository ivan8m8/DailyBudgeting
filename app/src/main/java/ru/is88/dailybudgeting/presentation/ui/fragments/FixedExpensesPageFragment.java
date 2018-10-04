package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
import ru.is88.dailybudgeting.presentation.ui.activities.MainActivity;

public class FixedExpensesPageFragment extends Fragment implements MainPresenter.View<FixedExpense> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_accounts, container, false);

        return viewRoot;
    }

    @Override
    public void showItemList(List<FixedExpense> fixedExpenses) {

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

    public static FixedExpensesPageFragment newInstance(int year, int month) {
        FixedExpensesPageFragment fixedExpensesPageFragment = new FixedExpensesPageFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.YEAR_KEY, year);
        args.putInt(MainActivity.MONTH_KEY, month);
        fixedExpensesPageFragment.setArguments(args);
        return fixedExpensesPageFragment;
    }
}
