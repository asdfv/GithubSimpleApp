package by.grodno.vasili.githubsimpleapp.feature.users;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import by.grodno.vasili.domain.interactor.GetUsersUseCase;

/**
 * ViewModel factory for {@link UsersActivity}
 */
class UsersViewModelFactory implements ViewModelProvider.Factory {
    private final GetUsersUseCase getUsersUseCase;
    private final UserItemMapper mapper;

    UsersViewModelFactory(GetUsersUseCase getUsersUseCase, UserItemMapper mapper) {
        this.getUsersUseCase = getUsersUseCase;
        this.mapper = mapper;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UsersViewModel.class)) {
            return (T) new UsersViewModel(getUsersUseCase, mapper);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
