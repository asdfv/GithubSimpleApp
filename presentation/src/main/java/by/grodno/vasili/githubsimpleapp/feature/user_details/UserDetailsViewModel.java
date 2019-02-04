package by.grodno.vasili.githubsimpleapp.feature.user_details;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import by.grodno.vasili.domain.interactor.GetUserUseCase;
import by.grodno.vasili.domain.model.User;
import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * ViewModel class for {@link UserDetailsActivity}
 */
class UserDetailsViewModel extends ViewModel {
    private final GetUserUseCase getUserUseCase;
    private final UserDetailsItemMapper mapper;
    private final MutableLiveData<UserDetailsItem> liveData;

    UserDetailsViewModel(GetUserUseCase getUserUseCase, UserDetailsItemMapper mapper) {
        this.getUserUseCase = getUserUseCase;
        this.mapper = mapper;
        liveData = new MutableLiveData<>();
    }

    /**
     * Asynchronously load user
     *
     * @param login Login of user
     */
    void loadUserAsync(@Nullable String login) {
        DisposableMaybeObserver<User> observer = new DisposableMaybeObserver<User>() {
            @Override
            public void onSuccess(User user) {
                liveData.setValue(mapper.map(user));
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error loading user %s", login);
            }

            @Override
            public void onComplete() {
                Timber.i("No user found with login %s", login);
            }
        };
        getUserUseCase.execute(observer, GetUserUseCase.Params.create(login));
    }

    MutableLiveData<UserDetailsItem> getLiveData() {
        return liveData;
    }
}
