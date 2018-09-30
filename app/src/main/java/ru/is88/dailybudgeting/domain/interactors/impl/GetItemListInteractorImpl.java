package ru.is88.dailybudgeting.domain.interactors.impl;

import java.util.List;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetItemListInteractor;

public class GetItemListInteractorImpl<T> extends AbstractInteractor implements GetItemListInteractor {

    private int mYear;
    private int mMonth;

    private Repository<T> mRepository;
    private GetItemListInteractor.Callback<T> mCallback;

    public GetItemListInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     int year,
                                     int month,
                                     Repository<T> repository,
                                     Callback<T> callback) {
        super(threadExecutor, mainThread);

        if (repository == null || callback == null) {
            throw new IllegalArgumentException("Either Repository<T> or GetItemListInteractor.Callback is null");
        }

        mYear = year;
        mMonth = month;
        mRepository = repository;
        mCallback = callback;
    }

    @Override
    public void run() {

        final List<T> list = mRepository.getItemList(mYear, mMonth);

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onItemListRetrieved(list);
            }
        });
    }
}
