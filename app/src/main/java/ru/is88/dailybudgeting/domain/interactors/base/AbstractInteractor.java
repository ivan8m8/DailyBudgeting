package ru.is88.dailybudgeting.domain.interactors.base;

import ru.is88.dailybudgeting.domain.executor.Executor;
import ru.is88.dailybudgeting.domain.executor.MainThread;

/**
 * This abstract class implements common methods for all the interactors.
 */

public abstract class AbstractInteractor implements Interactor {

    /**
     * The following params are declared volatile
     * as we might call isRunning() from different threads (mainly from UI).
     * For example, when an activity is getting destroyed we should probably cancel all the interactors,
     * but the request comes from the main thread unless it was specifically assigned to a background thread.
     */
    protected volatile boolean isRunning;
    protected volatile boolean isCanceled;

    protected Executor executor;
    protected MainThread mainThread;

    @Override
    public void execute() {
        isRunning = true;
        //
    }

    public void cancel(){
        isCanceled = true;
        isRunning = false;
    }

    public void onFinished(){
        isRunning = false;
        isCanceled = false;
    }

    public boolean isRunning(){
        return isRunning;
    }
}
