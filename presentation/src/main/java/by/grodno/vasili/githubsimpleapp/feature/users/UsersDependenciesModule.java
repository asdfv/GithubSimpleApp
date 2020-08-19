package by.grodno.vasili.githubsimpleapp.feature.users;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import by.grodno.vasili.data.datasource.retrofit.RetrofitUserDatasource;
import by.grodno.vasili.data.repository.UserDataRepository;
import by.grodno.vasili.domain.interactor.GetUsersUseCase;
import by.grodno.vasili.githubsimpleapp.thread.IOThread;
import by.grodno.vasili.githubsimpleapp.thread.UIThread;

/**
 * Dependency factory for {@link UsersActivity}
 */
class UsersDependenciesModule {
    private static final DiffUtil.ItemCallback<UserItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserItem oldItem, @NonNull UserItem newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserItem oldItem, @NonNull UserItem newItem) {
            return oldItem.equals(newItem);
        }
    };
    private final UsersViewModelFactory factory;
    private final UsersAdapter adapter;

    UsersDependenciesModule() {
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(
                new IOThread(),
                new UIThread(),
                new UserDataRepository(new RetrofitUserDatasource())
        );
        UserItemDatasourceFactory datasourceFactory = new UserItemDatasourceFactory(getUsersUseCase);
        factory = new UsersViewModelFactory(getUsersUseCase, datasourceFactory);
        adapter = new UsersAdapter(DIFF_CALLBACK);
    }

    UsersViewModelFactory getFactory() {
        return factory;
    }

    UsersAdapter getAdapter() {
        return adapter;
    }
}
