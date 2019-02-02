package by.grodno.vasili.githubsimpleapp.feature.users;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import by.grodno.vasili.domain.interactor.GetUsersUseCase;
import by.grodno.vasili.domain.model.User;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * View model for activity witch present list of users
 */
class UsersViewModel extends ViewModel {
    private final GetUsersUseCase getUsersUseCase;
    private final UserItemMapper mapper;
    private final MutableLiveData<List<UserItem>> liveData;

    UsersViewModel(GetUsersUseCase getUsersUseCase, UserItemMapper mapper) {
        this.getUsersUseCase = getUsersUseCase;
        this.mapper = mapper;
        liveData = new MutableLiveData<>();
    }

    /**
     * Asynchronously load Users
     * @param since The integer ID of the last User that you've seen
     */
    void loadUsers(int since) {
        DisposableSingleObserver<List<User>> observer = new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                liveData.setValue(mapper.mapList(users));
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error while retrieving Users");
            }
        };
        getUsersUseCase.execute(observer, GetUsersUseCase.Params.create(since));
    }

    MutableLiveData<List<UserItem>> getLiveData() {
        return liveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        getUsersUseCase.dispose();
    }
}
