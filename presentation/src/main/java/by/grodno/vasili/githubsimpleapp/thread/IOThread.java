package by.grodno.vasili.githubsimpleapp.thread;


import by.grodno.vasili.domain.threads.SubscriberThread;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Thread for IO-bound work
 */
public class IOThread implements SubscriberThread {
    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
