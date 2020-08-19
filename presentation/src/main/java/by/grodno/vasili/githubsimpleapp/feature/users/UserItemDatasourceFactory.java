package by.grodno.vasili.githubsimpleapp.feature.users;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import by.grodno.vasili.domain.interactor.GetUsersUseCase;

/**
 * Factory for create {@link UserItemDatasource} and store it in LiveData
 */
public class UserItemDatasourceFactory extends DataSource.Factory<String, UserItem> {
    private final GetUsersUseCase getUsersUseCase;
    private final MutableLiveData<PageKeyedDataSource<String, UserItem>> dataSourceLiveData;

    UserItemDatasourceFactory(GetUsersUseCase getUsersUseCase) {
        this.getUsersUseCase = getUsersUseCase;
        dataSourceLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<String, UserItem> create() {
        UserItemDatasource datasource = new UserItemDatasource(getUsersUseCase);
        dataSourceLiveData.postValue(datasource);
        return datasource;
    }

    MutableLiveData<PageKeyedDataSource<String, UserItem>> getDataSourceLiveData() {
        return dataSourceLiveData;
    }
}
