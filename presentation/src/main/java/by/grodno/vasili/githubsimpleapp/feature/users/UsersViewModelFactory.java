package by.grodno.vasili.githubsimpleapp.feature.users;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import by.grodno.vasili.domain.interactor.GetUsersUseCase;
import by.grodno.vasili.githubsimpleapp.model.UserItemMapper;

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
