package by.grodno.vasili.githubsimpleapp.feature.user_details;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import by.grodno.vasili.githubsimpleapp.R;
import by.grodno.vasili.githubsimpleapp.databinding.ActivityUserDetailsBinding;
import by.grodno.vasili.githubsimpleapp.feature.base.BaseActivity;
import timber.log.Timber;

/**
 * Activity witch represent Detail info for User
 */
public class UserDetailsActivity extends BaseActivity<ActivityUserDetailsBinding> {
    public static final String LOGIN_KEY = "by.grodno.vasili.githubsimpleapp.feature.user_details.EXTRA.LOGIN";

    private UserDetailsViewModel model;
    private UserDetailsDependenciesModule dependencies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dependencies = new UserDetailsDependenciesModule();
        model = ViewModelProviders.of(this, dependencies.getFactory()).get(UserDetailsViewModel.class);
        loadUserAsync();
        model.getLiveData().observe(this, binding::setUser);
    }

    private void loadUserAsync() {
        String login = getIntent().getStringExtra(LOGIN_KEY);
        if (login == null) {
            Timber.w("Error taking extra with user login from intent");
            showToast("Sorry, we can`t show you this user details");
        } else {
            model.loadUserAsync(login);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_user_details;
    }
}
