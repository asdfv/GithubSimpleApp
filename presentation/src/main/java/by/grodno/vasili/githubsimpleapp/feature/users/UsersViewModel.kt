package by.grodno.vasili.githubsimpleapp.feature.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import by.grodno.vasili.domain.interactor.GetUsersUseCase
import by.grodno.vasili.domain.model.User
import timber.log.Timber
import java.util.concurrent.Executors

/**
 * View model for activity witch present list of users
 */
internal class UsersViewModel(
        private val getUsersUseCase: GetUsersUseCase,
        private val sourceFactory: UserItemDatasourceFactory
) : ViewModel() {
    private val dataSourceLiveData: MutableLiveData<PageKeyedDataSource<String, UserItem>> = sourceFactory.dataSourceLiveData

    val pagedListLiveData: LiveData<PagedList<UserItem>> by lazy {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build()
        LivePagedListBuilder(sourceFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }

    /**
     * Invalidate datasource with users
     */
    fun invalidateDatasource() {
        val datasource = dataSourceLiveData.value
        if (datasource != null) {
            datasource.invalidate()
        } else {
            Timber.w("DatasourceLiveData not contains datasource")
        }
    }

    override fun onCleared() {
        super.onCleared()
        getUsersUseCase.dispose()
    }
}