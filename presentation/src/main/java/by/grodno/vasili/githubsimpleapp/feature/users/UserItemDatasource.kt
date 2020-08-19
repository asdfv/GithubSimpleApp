package by.grodno.vasili.githubsimpleapp.feature.users

import androidx.paging.PageKeyedDataSource
import by.grodno.vasili.domain.interactor.GetUsersUseCase
import by.grodno.vasili.domain.model.User
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

/**
 * Datasource for pagination.
 */
class UserItemDatasource(private val getUsersUseCase: GetUsersUseCase) : PageKeyedDataSource<String, UserItem>() {
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, UserItem>) {
        val observer: DisposableSingleObserver<List<User>> = object : DisposableSingleObserver<List<User>>() {
            override fun onSuccess(users: List<User>) {
                callback.onResult(users.map { UserItem(it) }, null, lastUserId(users))
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "Error while retrieving Users")
            }
        }
        getUsersUseCase.execute(observer, GetUsersUseCase.Params.create(START_POSITION))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {}
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, UserItem>) {
        val observer: DisposableSingleObserver<List<User>> = object : DisposableSingleObserver<List<User>>() {
            override fun onSuccess(users: List<User>) {
                callback.onResult(users.map { UserItem(it) }, lastUserId(users))
            }

            override fun onError(e: Throwable) {
                Timber.e(e, "Error while retrieving Users")
            }
        }
        getUsersUseCase.execute(observer, GetUsersUseCase.Params.create(params.key.toInt()))
    }

    private fun lastUserId(users: List<User>): String {
        return users[users.size - 1].id
    }

    companion object {
        private const val START_POSITION = 0
    }
}
