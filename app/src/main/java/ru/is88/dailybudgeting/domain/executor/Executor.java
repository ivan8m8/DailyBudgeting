package ru.is88.dailybudgeting.domain.executor;

import ru.is88.dailybudgeting.domain.interactors.base.AbstractInteractor;

/**
 * Executor is responsible for starting interactors.
 */

public interface Executor {

    /**
     * This method should call the interactor's execute() method and thus start the interactor.
     * And it should be called from a background thread since interactors might do lengthy operations.
     *
     * @param abstractInteractor is the interactor to run
     */
    void execute(final AbstractInteractor abstractInteractor);
}
