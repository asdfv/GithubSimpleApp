package by.grodno.vasili.githubsimpleapp.feature.user_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.grodno.vasili.domain.interactor.GetUserUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel class for [UserDetailsActivity]
 */
internal class UserDetailsViewModel(
        private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val liveData: MutableLiveData<UserDetailsItem> = MutableLiveData()

    /**
     * Asynchronously load user
     *
     * @param login Login of user
     */
    fun loadUserAsync(login: String) = viewModelScope.launch {
        val user = getUserUseCase.execute(GetUserUseCase.Params(login))
        liveData.value = UserDetailsItem(user)
    }

    override fun onCleared() {
        getUserUseCase.dispose()
    }
}
