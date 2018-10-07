package ru.is88.dailybudgeting.domain.executor.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;

public class ThreadExecutor implements Executor {

    // This is a singleton
    private static volatile ThreadExecutor sThreadExecutor;

    private static final int               CORE_PULL_SIZE  = 3;
    private static final int               MAX_POOL_SIZE   = 5;
    private static final int               KEEP_ALIVE_TIME = 120;
    private static final TimeUnit          TIME_UNIT       = TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> WORK_QUEUE      = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadExecutor() {
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_PULL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                WORK_QUEUE
        );
    }

    @Override
    public void execute(final AbstractInteractor abstractInteractor) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                abstractInteractor.run();
                abstractInteractor.onFinished();
            }
        });
    }

    /**
     * Returns a singleton instance of this executor.
     */
    public static Executor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }
        return sThreadExecutor;
    }
}
