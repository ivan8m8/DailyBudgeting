package ru.is88.dailybudgeting.presentation.presenters;

/**
 * Except onError(), all the following methods control the view lifecycle.
 * And they should be called in the appropriate view lifecycle methods.
 */

public interface BasePresenter {

    void resume();
    void pause();
    void stop();
    void destroy();

    /**
     * Signals the appropriate view that an error occurs.
     * @param message is an error message.
     */
    void onError(String message);
}
