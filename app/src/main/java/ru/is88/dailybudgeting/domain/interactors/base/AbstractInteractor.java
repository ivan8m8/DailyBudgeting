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

    protected Executor threadExecutor;
    protected MainThread mainThread;

    public AbstractInteractor(Executor threadExecutor, MainThread mainThread){
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
    }

    /**
     * This method contains the actual business logic of the interactor.
     * It SHOULDN'T BE CALLED DIRECTLY, but, instead, a developer should call
     * execute() method of an interactor to make sure the operation is done on a background thread.
     * </p>
     * This method should only be called directly while doing unit/integration tests.
     */
    public abstract void run();

    @Override
    public void execute() {
        isRunning = true;
        threadExecutor.execute(this);
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
