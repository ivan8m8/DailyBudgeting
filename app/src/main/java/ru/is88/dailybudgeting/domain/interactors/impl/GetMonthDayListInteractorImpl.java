//package ru.is88.dailybudgeting.domain.interactors.impl;
//
//import java.util.List;
//
//import ru.is88.dailybudgeting.domain.mExecutor.Executor;
//import ru.is88.dailybudgeting.domain.mExecutor.MainThread;
//import ru.is88.dailybudgeting.domain.interactors.GetItemListInteractor;
//import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
//import ru.is88.dailybudgeting.domain.models.MonthDay;
//import ru.is88.dailybudgeting.domain.Repository;
//
//public class GetMonthDayListInteractorImpl extends AbstractInteractor implements GetItemListInteractor {
//
//    private int mMonth;
//    private int mYear;
//
//    private Repository<MonthDay> mMonthDayRepository;
//    private GetItemListInteractor.Callback<MonthDay> mCallback;
//
//    public GetMonthDayListInteractorImpl(Executor threadExecutor,
//                                         MainThread mMainThread,
//                                         int month,
//                                         int year,
//                                         Repository<MonthDay> monthDayRepository,
//                                         GetItemListInteractor.Callback<MonthDay> callback) {
//        super(threadExecutor, mMainThread);
//
//        if (monthDayRepository == null || callback == null) {
//            throw new IllegalArgumentException("Either Repository<MonthDay> or GetItemListInteractor.Callback is null");
//        }
//
//        mMonth = month;
//        mYear = year;
//        mMonthDayRepository = monthDayRepository;
//        mCallback = callback;
//    }
//
//    @Override
//    public void run() {
//
//        final List<MonthDay> monthDays = mMonthDayRepository.getItemList(mMonth, mYear);
//
//        mMainThread.post(new Runnable() {
//            @Override
//            public void run() {
//                mCallback.onItemListRetrieved(monthDays);
//            }
//        });
//    }
//}
