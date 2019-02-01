package by.grodno.vasili.githubsimpleapp.thread;

import javax.inject.Inject;

import by.grodno.vasili.domain.threads.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Main thread for executing process for UI
 */
public class UIThread implements PostExecutionThread {
    @Inject
    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
