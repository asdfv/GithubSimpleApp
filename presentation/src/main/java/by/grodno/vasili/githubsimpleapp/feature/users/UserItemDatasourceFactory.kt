package by.grodno.vasili.githubsimpleapp.feature.users

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import by.grodno.vasili.domain.interactor.GetUsersUseCase
import kotlinx.coroutines.CoroutineScope

/**
 * Factory for create [UserItemDatasource] and store it in LiveData
 */
class UserItemDatasourceFactory(
        private val getUsersUseCase: GetUsersUseCase,
        private val scope: CoroutineScope
) : DataSource.Factory<String, UserItem>() {
    val dataSourceLiveData: MutableLiveData<PageKeyedDataSource<String, UserItem>> = MutableLiveData()

    override fun create(): DataSource<String, UserItem> {
        val datasource = UserItemDatasource(getUsersUseCase, scope)
        dataSourceLiveData.postValue(datasource)
        return datasource
    }
}
