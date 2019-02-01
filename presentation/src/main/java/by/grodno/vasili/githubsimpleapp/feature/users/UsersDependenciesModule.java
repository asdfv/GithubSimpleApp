package by.grodno.vasili.githubsimpleapp.feature.users;

import by.grodno.vasili.data.datasource.retrofit.RetrofitUserDatasource;
import by.grodno.vasili.data.repository.UserDataRepository;
import by.grodno.vasili.data.response.UserMapper;
import by.grodno.vasili.domain.interactor.GetUsersUseCase;
import by.grodno.vasili.githubsimpleapp.model.UserItemMapper;
import by.grodno.vasili.githubsimpleapp.thread.IOThread;
import by.grodno.vasili.githubsimpleapp.thread.UIThread;

class UsersDependenciesModule {
    private final UsersViewModelFactory factory;

    UsersDependenciesModule() {
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(new IOThread(), new UIThread(), new UserDataRepository(new RetrofitUserDatasource(), new UserMapper()));
        UserItemMapper mapper = new UserItemMapper();
        factory = new UsersViewModelFactory(getUsersUseCase, mapper);
    }

    UsersViewModelFactory getFactory() {
        return factory;
    }
}
