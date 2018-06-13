package ru.is88.dailybudgeting.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.is88.dailybudgeting.R;
import ru.is88.dailybudgeting.presentation.ui.adapters.MonthDaysRecyclerAdapter;
import ru.is88.dailybudgeting.utils.Utils;

public class PageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_page, container, false);

        RecyclerView recyclerView = viewRoot.findViewById(R.id.monthDaysRecyclerView);
        MonthDaysRecyclerAdapter monthDaysRecyclerAdapter = new MonthDaysRecyclerAdapter();
        recyclerView.setAdapter(monthDaysRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.scrollToPosition(15);

        return viewRoot;
    }

    /**
     * This method is called within MyFragmentPagerAdapter.getItem(int position)
     * @param month !!!
     */
    public static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        return pageFragment;
    }
}
