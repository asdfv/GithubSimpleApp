package by.grodno.vasili.githubsimpleapp.feature.users;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import by.grodno.vasili.githubsimpleapp.R;
import by.grodno.vasili.githubsimpleapp.databinding.ActivityUsersBinding;
import by.grodno.vasili.githubsimpleapp.feature.base.BaseActivity;
import by.grodno.vasili.githubsimpleapp.feature.user_details.UserDetailsActivity;
import timber.log.Timber;

/**
 * Activity represent list of Users
 */
public class UsersActivity extends BaseActivity<ActivityUsersBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UsersDependenciesModule dependencies = new UsersDependenciesModule();
        UsersAdapter adapter = dependencies.getAdapter();
        UsersViewModel model = ViewModelProviders.of(this, dependencies.getFactory()).get(UsersViewModel.class);
        initRecyclerView(adapter);
        MutableLiveData<PagedList<UserItem>> liveData = model.getLiveData();
        liveData.observe(this, adapter::submitList);
        model.loadUsersAsync(liveData);
    }

    /**
     * Handler for click on recycler view item
     * @param login Login of user in recycler view item
     */
    public void onItemClick(String login) {
        if (login == null) {
            showToast("Sorry, we can`t show you this user details");
            Timber.e("Empty login in recycler view");
            return;
        }
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(UserDetailsActivity.LOGIN_KEY, login);
        startActivity(intent);
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
