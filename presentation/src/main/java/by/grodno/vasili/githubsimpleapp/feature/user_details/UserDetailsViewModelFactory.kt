package by.grodno.vasili.githubsimpleapp.feature.user_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.grodno.vasili.domain.interactor.GetUserUseCase

/**
 * ViewModel factory for [UserDetailsActivity]
 */
internal class UserDetailsViewModelFactory(private val getUserUseCase: GetUserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(getUserUseCase) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }
}
