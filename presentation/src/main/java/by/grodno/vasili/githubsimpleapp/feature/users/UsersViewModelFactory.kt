package by.grodno.vasili.githubsimpleapp.feature.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.grodno.vasili.domain.interactor.GetUsersUseCase

/**
 * ViewModel factory for [UsersActivity]
 */
internal class UsersViewModelFactory(private val getUsersUseCase: GetUsersUseCase,
                                     private val sourceFactory: UserItemDatasourceFactory) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(getUsersUseCase, sourceFactory) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }
}