package by.grodno.vasili.githubsimpleapp.feature.users;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import by.grodno.vasili.domain.interactor.GetUsersUseCase;
import by.grodno.vasili.domain.model.User;
import by.grodno.vasili.githubsimpleapp.model.UserItem;
import by.grodno.vasili.githubsimpleapp.model.UserItemMapper;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

/**
 * View model for activity witch present list of users
 */
class UsersViewModel extends ViewModel {
    private final GetUsersUseCase getUsersUseCase;
    private final UserItemMapper mapper;
    private MutableLiveData<List<UserItem>> usersLiveData;

    UsersViewModel(GetUsersUseCase getUsersUseCase, UserItemMapper mapper) {
        this.getUsersUseCase = getUsersUseCase;
        this.mapper = mapper;
    }

    /**
     * Asynchronously load Users
     * @param since The integer ID of the last User that you've seen
     */
    void loadUsers(int since) {
        DisposableSingleObserver<List<User>> observer = new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                usersLiveData.setValue(mapper.mapList(users));
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error while retrieving Users");
            }
        };
        getUsersUseCase.execute(observer, GetUsersUseCase.Params.create(since));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        getUsersUseCase.dispose();
    }

    MutableLiveData<List<UserItem>> getUsersLiveData() {
        usersLiveData = defaultIfNull(usersLiveData, new MutableLiveData<>());
        return usersLiveData;
    }
}
