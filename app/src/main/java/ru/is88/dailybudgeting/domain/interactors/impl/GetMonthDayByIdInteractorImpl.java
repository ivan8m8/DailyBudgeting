//package ru.is88.dailybudgeting.domain.interactors.impl;
//
//import ru.is88.dailybudgeting.domain.mExecutor.Executor;
//import ru.is88.dailybudgeting.domain.mExecutor.MainThread;
//import ru.is88.dailybudgeting.domain.interactors.GetItemByIdInteractor;
//import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
//import ru.is88.dailybudgeting.domain.models.MonthDay;
//import ru.is88.dailybudgeting.domain.Repository;
//
//public class GetMonthDayByIdInteractorImpl extends AbstractInteractor implements GetItemByIdInteractor {
//
//    private int mMonthDayId;
//    private Repository<MonthDay> mMonthDayRepository;
//    private GetItemByIdInteractor.Callback<MonthDay> mCallback;
//
//    public GetMonthDayByIdInteractorImpl(Executor threadExecutor, MainThread mMainThread,
//                                         int monthDayId,
//                                         Repository<MonthDay> monthDayRepository,
//                                         GetItemByIdInteractor.Callback<MonthDay> callback) {
//        super(threadExecutor, mMainThread);
//        mMonthDayId = monthDayId;
//        mMonthDayRepository = monthDayRepository;
//        mCallback = callback;
//    }
//
//    @Override
//    public void run() {
//        final MonthDay monthDay = mMonthDayRepository.getItemById(mMonthDayId);
//        if (monthDay == null) {
//            mMainThread.post(new Runnable() {
//                @Override
//                public void run() {
//                    mCallback.onItemNotFound();
//                }
//            });
//        } else {
//            mMainThread.post(new Runnable() {
//                @Override
//                public void run() {
//                    mCallback.onItemRetrieved(monthDay);
//                }
//            });
//        }
//    }
//}
