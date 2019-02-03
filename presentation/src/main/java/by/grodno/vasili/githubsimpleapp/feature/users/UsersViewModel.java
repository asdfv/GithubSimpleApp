package by.grodno.vasili.githubsimpleapp.feature.users;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import by.grodno.vasili.domain.interactor.GetUsersUseCase;

/**
 * View model for activity witch present list of users
 */
class UsersViewModel extends ViewModel {
    private static final int PAGE_SIZE = 10;
    private final GetUsersUseCase getUsersUseCase;
    private final UserItemDatasource datasource;
    private MutableLiveData<PagedList<UserItem>> liveData;

    UsersViewModel(GetUsersUseCase getUsersUseCase, UserItemDatasource datasource) {
        this.getUsersUseCase = getUsersUseCase;
        this.datasource = datasource;
    }

    /**
     * Create {@link PagedList} and set into liveData
     */
    void loadUsersAsync(MutableLiveData<PagedList<UserItem>> liveData) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        PagedList<UserItem> pagedList = new PagedList.Builder<>(datasource, config)
                .setNotifyExecutor(new MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();
        liveData.postValue(pagedList);
    }

    MutableLiveData<PagedList<UserItem>> getLiveData() {
        if (liveData != null) {
            return liveData;
        }
        liveData = new MutableLiveData<>();
        return liveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        getUsersUseCase.dispose();
    }

    /**
     * Executor for run in UI thread
     */
    class MainThreadExecutor implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mHandler.post(command);
        }
    }
}
