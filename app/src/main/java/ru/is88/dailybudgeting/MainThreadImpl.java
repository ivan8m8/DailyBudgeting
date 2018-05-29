package ru.is88.dailybudgeting;

import android.os.Handler;
import android.os.Looper;

import ru.is88.dailybudgeting.domain.executor.MainThread;

/**
 * This single tone class makes sure that the runnable we passed will be run on the main UI thread.
 */

public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;
    private Handler handler;

    private MainThreadImpl(){
        handler = new Handler(Looper.getMainLooper());
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }
        return sMainThread;
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
