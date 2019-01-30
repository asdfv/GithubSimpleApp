package by.grodno.vasili.domain.interactor;

import by.grodno.vasili.domain.model.User;
import by.grodno.vasili.domain.repository.UserRepository;
import by.grodno.vasili.domain.threads.PostExecutionThread;
import by.grodno.vasili.domain.threads.SubscriberThread;
import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Use case for retrieving one {@link User} from repository
 */
public class GetUserUseCase extends UseCase<DisposableMaybeObserver<User>, GetUserUseCase.Params> {
    private final SubscriberThread subscriberThread;
    private final PostExecutionThread postExecutionThread;
    private final UserRepository repository;
    private final CompositeDisposable disposables;

    public GetUserUseCase(SubscriberThread subscriberThread, PostExecutionThread postExecutionThread, UserRepository repository) {
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
    public void execute(DisposableMaybeObserver<User> observer, Params params) {
        final Maybe<User> observable = repository.getOne(params.username)
                .subscribeOn(subscriberThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        disposables.add(observable.subscribeWith(observer));
    }

    public static final class Params {
        private final String username;

        private Params(String username) {
            this.username = username;
        }

        public static Params create(String username) {
            return new Params(username);
        }
    }
}
