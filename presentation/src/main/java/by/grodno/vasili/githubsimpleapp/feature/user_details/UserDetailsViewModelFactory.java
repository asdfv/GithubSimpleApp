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
    private final UserDetailsItemMapper mapper;

    UserDetailsViewModelFactory(GetUserUseCase getUserUseCase, UserDetailsItemMapper mapper) {
        this.getUserUseCase = getUserUseCase;
        this.mapper = mapper;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserDetailsViewModel.class)) {
            return (T) new UserDetailsViewModel(getUserUseCase, mapper);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
