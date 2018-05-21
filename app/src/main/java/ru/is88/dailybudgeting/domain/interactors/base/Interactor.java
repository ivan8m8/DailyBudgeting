package ru.is88.dailybudgeting.domain.interactors.base;

public interface Interactor {

    /**
     * This is the main method starts an interactor.
     * It ensures all operations is done on a background thread.
     */
    void execute();
}
