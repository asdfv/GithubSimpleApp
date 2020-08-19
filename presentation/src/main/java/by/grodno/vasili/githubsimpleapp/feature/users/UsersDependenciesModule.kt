package by.grodno.vasili.githubsimpleapp.feature.users

import androidx.recyclerview.widget.DiffUtil
import by.grodno.vasili.data.datasource.retrofit.RetrofitUserDatasource
import by.grodno.vasili.data.repository.UserDataRepository
import by.grodno.vasili.domain.interactor.GetUsersUseCase
import by.grodno.vasili.githubsimpleapp.BuildConfig
import kotlinx.coroutines.CoroutineScope

/**
 * Dependency factory for [UsersActivity]
 */
internal class UsersDependenciesModule(scope: CoroutineScope) {
    val factory: UsersViewModelFactory
    val adapter: UsersAdapter

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<UserItem> = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    init {
        val getUsersUseCase = GetUsersUseCase(UserDataRepository(RetrofitUserDatasource(BuildConfig.GITHUB_TOKEN)))
        val datasourceFactory = UserItemDatasourceFactory(getUsersUseCase, scope)
        factory = UsersViewModelFactory(getUsersUseCase, datasourceFactory)
        adapter = UsersAdapter(DIFF_CALLBACK)
    }
}