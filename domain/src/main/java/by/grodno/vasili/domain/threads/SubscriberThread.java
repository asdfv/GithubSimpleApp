package by.grodno.vasili.domain.threads;

import io.reactivex.Scheduler;

/**
 * Thread abstraction. In this thread observable source emits elements
 */
public interface SubscriberThread {
    Scheduler getScheduler();
}
