package by.grodno.vasili.githubsimpleapp.feature.users;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import by.grodno.vasili.domain.interactor.GetUsersUseCase;
import by.grodno.vasili.domain.model.User;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * Datasource for pagination
 */
public class UserItemDatasource extends PageKeyedDataSource<String, UserItem> {
    private static final int START_POSITION = 0;
    private final GetUsersUseCase getUsersUseCase;
    private final UserItemMapper mapper;

    UserItemDatasource(GetUsersUseCase getUsersUseCase, UserItemMapper mapper) {
        this.getUsersUseCase = getUsersUseCase;
        this.mapper = mapper;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, UserItem> callback) {
        DisposableSingleObserver<List<User>> observer = new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                callback.onResult(mapper.mapList(users), null, lastUserId(users));
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error while retrieving Users");
            }
        };

        getUsersUseCase.execute(observer, GetUsersUseCase.Params.create(START_POSITION));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, UserItem> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, UserItem> callback) {
        DisposableSingleObserver<List<User>> observer = new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                callback.onResult(mapper.mapList(users), lastUserId(users));
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error while retrieving Users");
            }
        };
        getUsersUseCase.execute(observer, GetUsersUseCase.Params.create(Integer.parseInt(params.key)));
    }

    private String lastUserId(List<User> users) {
        return users.get(users.size() - 1).id;
    }
}
