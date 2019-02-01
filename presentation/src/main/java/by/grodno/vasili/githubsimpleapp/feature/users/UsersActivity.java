package by.grodno.vasili.githubsimpleapp.feature.users;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import by.grodno.vasili.githubsimpleapp.R;
import by.grodno.vasili.githubsimpleapp.databinding.ActivityUsersBinding;
import by.grodno.vasili.githubsimpleapp.feature.base.BaseActivity;

/**
 * Activity represent list of Users
 */
public class UsersActivity extends BaseActivity<ActivityUsersBinding> {
    private UsersDependenciesModule dependencies;
    private UsersViewModel model;
    private UsersAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dependencies = new UsersDependenciesModule();
        adapter = new UsersAdapter();
        model = ViewModelProviders.of(this, dependencies.getFactory()).get(UsersViewModel.class);

        initRecyclerView(adapter);

        model.getUsersLiveData().observe(this, adapter::setItems);
        model.loadUsers(6877291);
    }

    /**
     * Handler for click on recycler view item
     * @param username Username of user in recycler view item
     */
    public void onItemClick(String username) {
        showToast(username);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_users;
    }

    private void initRecyclerView(UsersAdapter adapter) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }
}
