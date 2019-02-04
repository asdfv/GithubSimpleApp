package by.grodno.vasili.githubsimpleapp.feature.users;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import by.grodno.vasili.domain.interactor.GetUsersUseCase;
import timber.log.Timber;

/**
 * View model for activity witch present list of users
 */
class UsersViewModel extends ViewModel {
    private static final int PAGE_SIZE = 10;
    private final GetUsersUseCase getUsersUseCase;
    private final UserItemDatasourceFactory sourceFactory;
    private final MutableLiveData<PageKeyedDataSource<String, UserItem>> dataSourceLiveData;
    private LiveData<PagedList<UserItem>> pagedListLiveData;

    UsersViewModel(GetUsersUseCase getUsersUseCase, UserItemDatasourceFactory sourceFactory) {
        this.getUsersUseCase = getUsersUseCase;
        this.sourceFactory = sourceFactory;
        dataSourceLiveData = sourceFactory.getDataSourceLiveData();
    }

    /**
     * Init if needed paged live data
     *
     * @return live data for paginated user list
     */
    @NonNull
    LiveData<PagedList<UserItem>> initAndGetPagedListLiveData() {
        if (pagedListLiveData != null) {
            return pagedListLiveData;
        }
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        pagedListLiveData = new LivePagedListBuilder<>(sourceFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();
        return pagedListLiveData;
    }

    /**
     * Invalidate datasource with users
     */
    void invalidateDatasource() {
        PageKeyedDataSource<String, UserItem> datasource = dataSourceLiveData.getValue();
        if (datasource != null) {
            datasource.invalidate();
        } else {
            Timber.w("DatasourceLiveData not contains datasource");
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        getUsersUseCase.dispose();
    }
}
