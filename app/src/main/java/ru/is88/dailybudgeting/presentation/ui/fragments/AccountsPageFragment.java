//package ru.is88.dailybudgeting.presentation.ui.fragments;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ru.is88.dailybudgeting.MainThreadImpl;
//import ru.is88.dailybudgeting.R;
//import ru.is88.dailybudgeting.domain.executor.MainThread;
//import ru.is88.dailybudgeting.domain.executor.impl.ThreadExecutor;
//import ru.is88.dailybudgeting.domain.models.accounts.AbstractAccount;
//import ru.is88.dailybudgeting.domain.models.accounts.FixedExpense;
//import ru.is88.dailybudgeting.domain.models.accounts.Income;
//import ru.is88.dailybudgeting.presentation.presenters.MainPresenter;
//import ru.is88.dailybudgeting.presentation.presenters.impl.FixedExpenseMainPresenterImpl;
//import ru.is88.dailybudgeting.presentation.presenters.impl.IncomeMainPresenterImpl;
//import ru.is88.dailybudgeting.presentation.ui.adapters.AccountsRecyclerAdapter;
//import ru.is88.dailybudgeting.storage.IncomeRepositoryImpl;
//
//public class AccountsPageFragment extends Fragment
//        implements MainPresenter.View<? extends AbstractAccount> {
//
//    /**
//     * The fragment argument representing the section number for this
//     * fragment.
//     */
//    private static final String SECTION_NUMBER_KEY = "section_number_key";
//
//    private RecyclerView mRecyclerView;
//    private AccountsRecyclerAdapter mAccountsRecyclerAdapter;
//    private List<AbstractAccount> mAccounts;
//
//    private boolean mIsThisIncome = false;
//
//    private IncomeMainPresenterImpl mIncomeMainPresenterImpl;
//    private FixedExpenseMainPresenterImpl mFixedExpenseMainPresenter;
//
//    /**
//     * Returns a new instance of this fragment for the given section
//     * number.
//     */
//    public static AccountsPageFragment newInstance(int sectionNumber) {
//        AccountsPageFragment fragment = new AccountsPageFragment();
//        Bundle args = new Bundle();
//        args.putInt(SECTION_NUMBER_KEY, sectionNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            if (getArguments().getInt(SECTION_NUMBER_KEY) == 1) {
//                mIsThisIncome = true;
//            } else if (getArguments().getInt(SECTION_NUMBER_KEY) == 2) {
//                mIsThisIncome = false;
//            }
//        }
//
//        mIncomeMainPresenterImpl = new IncomeMainPresenterImpl(
//                ThreadExecutor.getInstance(),
//                MainThreadImpl.getInstance(),
//                new IncomeRepositoryImpl(),
//                this
//        );
//
//
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View viewRoot = inflater.inflate(R.layout.fragment_accounts, container, false);
//
//        mRecyclerView = viewRoot.findViewById(R.id.accountsRecyclerView);
//        initRecycler();
//
//        return viewRoot;
//    }
//
//    private void initRecycler() {
//        mAccounts = new ArrayList<>();
//        if (mIsThisIncome) {
//            initIncomeRecycler();
//        } else {
//            initExpensesRecycler();
//        }
//    }
//
//    private void initIncomeRecycler(){
//        //mAccounts.add(new Income(2018, 9, 1000, "зп от Кирилла"));
//        mAccountsRecyclerAdapter = new AccountsRecyclerAdapter(mAccounts);
//        mRecyclerView.setAdapter(mAccountsRecyclerAdapter);
//    }
//
//    private void initExpensesRecycler(){
//        //mAccounts.add(new FixedExpense(2018, 9, 23600, "аренда"));
//        mAccountsRecyclerAdapter = new AccountsRecyclerAdapter(mAccounts);
//        mRecyclerView.setAdapter(mAccountsRecyclerAdapter);
//    }
//
//}
