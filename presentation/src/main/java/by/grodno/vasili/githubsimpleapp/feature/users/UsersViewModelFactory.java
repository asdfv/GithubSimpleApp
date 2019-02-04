package by.grodno.vasili.githubsimpleapp.feature.users;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import by.grodno.vasili.domain.interactor.GetUsersUseCase;

/**
 * ViewModel factory for {@link UsersActivity}
 */
class UsersViewModelFactory implements ViewModelProvider.Factory {
    private final GetUsersUseCase getUsersUseCase;
    private final UserItemDatasourceFactory sourceFactory;

    UsersViewModelFactory(GetUsersUseCase getUsersUseCase, UserItemDatasourceFactory sourceFactory) {
        this.getUsersUseCase = getUsersUseCase;
        this.sourceFactory = sourceFactory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UsersViewModel.class)) {
            return (T) new UsersViewModel(getUsersUseCase, sourceFactory);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
