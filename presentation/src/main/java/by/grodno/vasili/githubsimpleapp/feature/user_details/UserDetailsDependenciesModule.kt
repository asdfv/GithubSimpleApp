package by.grodno.vasili.githubsimpleapp.feature.user_details

import by.grodno.vasili.data.datasource.retrofit.RetrofitUserDatasource
import by.grodno.vasili.data.repository.UserDataRepository
import by.grodno.vasili.domain.interactor.GetUserUseCase
import by.grodno.vasili.githubsimpleapp.BuildConfig

/**
 * Dependency factory for [UserDetailsActivity]
 */
internal class UserDetailsDependenciesModule {
    val factory: UserDetailsViewModelFactory

    init {
        val getUserUseCase = GetUserUseCase(UserDataRepository(RetrofitUserDatasource(BuildConfig.GITHUB_TOKEN)))
        factory = UserDetailsViewModelFactory(getUserUseCase)
    }
}