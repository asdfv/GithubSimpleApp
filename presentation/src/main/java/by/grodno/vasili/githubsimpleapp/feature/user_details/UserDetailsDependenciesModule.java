package by.grodno.vasili.githubsimpleapp.feature.user_details;


import by.grodno.vasili.data.datasource.retrofit.RetrofitUserDatasource;
import by.grodno.vasili.data.repository.UserDataRepository;
import by.grodno.vasili.domain.interactor.GetUserUseCase;
import by.grodno.vasili.githubsimpleapp.thread.IOThread;
import by.grodno.vasili.githubsimpleapp.thread.UIThread;

/**
 * Dependency factory for {@link UserDetailsActivity}
 */
class UserDetailsDependenciesModule {
    private final UserDetailsViewModelFactory factory;

    UserDetailsDependenciesModule() {
        GetUserUseCase getUserUseCase = new GetUserUseCase(new IOThread(), new UIThread(), new UserDataRepository(new RetrofitUserDatasource()));
        factory = new UserDetailsViewModelFactory(getUserUseCase);
    }

    UserDetailsViewModelFactory getFactory() {
        return factory;
    }
}
