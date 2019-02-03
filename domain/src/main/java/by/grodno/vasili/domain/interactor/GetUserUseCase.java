package by.grodno.vasili.domain.interactor;

import java.util.List;

import by.grodno.vasili.domain.model.Organization;
import by.grodno.vasili.domain.model.User;
import by.grodno.vasili.domain.repository.UserRepository;
import by.grodno.vasili.domain.threads.PostExecutionThread;
import by.grodno.vasili.domain.threads.SubscriberThread;
import io.reactivex.Maybe;
import io.reactivex.Single;
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
        String login = params.login;
        final Single<List<Organization>> organizationsObservable = repository.getOrganizations(login);
        final Maybe<User> userObservable = repository.getOne(login);
        Maybe<User> observable = userObservable
                .map(user -> {
                    user.organizations = organizationsObservable.blockingGet(); // TODO blockingGet?
                    return user;
                })
                .subscribeOn(subscriberThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        disposables.add(observable.subscribeWith(observer));
    }

    /**
     * Parameters for request one user from repository
     */
    public static final class Params {
        private final String login;

        private Params(String login) {
            this.login = login;
        }

        public static Params create(String login) {
            return new Params(login);
        }
    }
}
