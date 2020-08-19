package by.grodno.vasili.githubsimpleapp.feature.user_details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import by.grodno.vasili.domain.interactor.GetUserUseCase;

/**
 * ViewModel factory for {@link UserDetailsActivity}
 */
class UserDetailsViewModelFactory implements ViewModelProvider.Factory {
    private final GetUserUseCase getUserUseCase;

    UserDetailsViewModelFactory(GetUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserDetailsViewModel.class)) {
            return (T) new UserDetailsViewModel(getUserUseCase);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
