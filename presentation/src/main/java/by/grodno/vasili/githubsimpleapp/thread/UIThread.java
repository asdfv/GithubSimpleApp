package by.grodno.vasili.githubsimpleapp.thread;

import by.grodno.vasili.domain.threads.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Main thread for executing process for UI
 */
public class UIThread implements PostExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
