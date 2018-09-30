package ru.is88.dailybudgeting.presentation.presenters;

/**
 * Except onError(), all the following methods control the mView lifecycle.
 * And they should be called in the appropriate mView lifecycle methods.
 */

public interface BasePresenter {

    void resume();
    void pause();
    void stop();
    void destroy();

    /**
     * Signals the appropriate mView that an error occurs.
     * @param message is an error message.
     */
    void onError(String message);
}
