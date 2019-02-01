package by.grodno.vasili.domain.interactor;


import java.util.List;

import by.grodno.vasili.domain.model.User;
import by.grodno.vasili.domain.repository.UserRepository;
import by.grodno.vasili.domain.threads.PostExecutionThread;
import by.grodno.vasili.domain.threads.SubscriberThread;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Use case retrieving users list from repository
 */
public class GetUsersUseCase extends UseCase<DisposableSingleObserver<List<User>>, GetUsersUseCase.Params> {
    private final SubscriberThread subscriberThread;
    private final PostExecutionThread postExecutionThread;
    private final UserRepository repository;
    private final CompositeDisposable disposables;

    public GetUsersUseCase(SubscriberThread subscriberThread, PostExecutionThread postExecutionThread, UserRepository repository) {
        this.subscriberThread = subscriberThread;
        this.postExecutionThread = postExecutionThread;
        this.repository = repository;
        this.disposables = new CompositeDisposable();
    }

    @Override
    CompositeDisposable getDisposables() {
        return disposables;
    }

    @Override
    public void execute(DisposableSingleObserver<List<User>> observer, Params params) {
        final Single<List<User>> observable = repository.getAll(params.since)
                .subscribeOn(subscriberThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        disposables.add(observable.subscribeWith(observer));
    }

    /**
     * Parameters for request users list from repository
     */
    public static final class Params {
        private final int since;

        private Params(int since) {
            this.since = since;
        }

        public static GetUsersUseCase.Params create(int since) {
            return new GetUsersUseCase.Params(since);
        }
    }
}
