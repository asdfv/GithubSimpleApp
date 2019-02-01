package by.grodno.vasili.githubsimpleapp.feature.users;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import by.grodno.vasili.githubsimpleapp.R;

public class UsersActivity extends AppCompatActivity {
    private UsersDependenciesModule dependencies;
    private UsersViewModel model;
    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        label = findViewById(R.id.label);
        dependencies = new UsersDependenciesModule();

        model = ViewModelProviders.of(this, dependencies.getFactory()).get(UsersViewModel.class);
        model.getUsersLiveData().observe(this, users -> label.setText(String.valueOf(users.size())));
        model.loadUsers(6877291);
    }
}
