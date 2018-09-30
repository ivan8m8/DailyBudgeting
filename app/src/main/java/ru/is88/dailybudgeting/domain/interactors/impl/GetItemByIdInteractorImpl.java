package ru.is88.dailybudgeting.domain.interactors.impl;

import ru.is88.dailybudgeting.domain.Repository;
import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;
import ru.is88.dailybudgeting.domain.interactors.GetItemByIdInteractor;

public class GetItemByIdInteractorImpl<T> extends AbstractInteractor implements GetItemByIdInteractor {

    private long mId;
    private Repository<T> mRepository;
    private GetItemByIdInteractor.Callback<T> mCallback;

    public GetItemByIdInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     long id,
                                     Repository<T> repository,
                                     GetItemByIdInteractor.Callback<T> callback) {
        super(threadExecutor, mainThread);
        mId = id;
        mRepository = repository;
        mCallback = callback;
    }

    @Override
    public void run() {
        final T item = mRepository.getItemById(mId);
        if (item == null) {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onItemNotFound();
                }
            });
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onItemRetrieved(item);
                }
            });
        }
    }
}