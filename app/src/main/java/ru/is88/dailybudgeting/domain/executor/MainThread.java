package ru.is88.dailybudgeting.domain.executor;

/**
 * This interface defines a class that enables interactors to run certain operations on the UI thread.
 */

public interface MainThread {
    void post(final Runnable runnable);
}
