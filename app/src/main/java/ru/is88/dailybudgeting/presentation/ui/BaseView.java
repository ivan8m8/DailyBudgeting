package ru.is88.dailybudgeting.presentation.ui;

/**
 * This interface consists of basic methods to show user responses from a background thread.
 * All the views should implement this interface.
 */

public interface BaseView {

    /**
     * This is a typical method to show some progress information during a background task.
     * Also it's good to disable some buttons and other that related to hit a background task.
     */
    void onProgressStarted();

    /**
     * In contrast, this is a typical method where all the additional dialogs should be hidden.
     * It means a background task is finished and the buttons starts it should be visible again.
     */
    void onProgressFinished();

    /**
     * To show errors on the UI.
     * @param message is an error message to be displayed.
     */
    void showError (String message);
}
