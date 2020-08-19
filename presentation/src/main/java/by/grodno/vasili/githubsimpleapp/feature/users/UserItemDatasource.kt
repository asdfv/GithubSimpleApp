package by.grodno.vasili.githubsimpleapp.feature.users

import androidx.paging.PageKeyedDataSource
import by.grodno.vasili.domain.interactor.GetUsersUseCase
import by.grodno.vasili.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Datasource for pagination.
 */
class UserItemDatasource(
        private val getUsersUseCase: GetUsersUseCase,
        private val scope: CoroutineScope
) : PageKeyedDataSource<String, UserItem>() {
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, UserItem>) {
        scope.launch {
            val users = getUsersUseCase.execute(GetUsersUseCase.Params(0))
            callback.onResult(users.map { UserItem(it) }, null, lastUserId(users))
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {}

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {
        scope.launch {
            val users = getUsersUseCase.execute(GetUsersUseCase.Params(params.key.toInt()))
            callback.onResult(users.map { UserItem(it) }, lastUserId(users))
        }
    }

    private fun lastUserId(users: List<User>): String {
        return users[users.size - 1].id
    }
}
