package by.grodno.vasili.githubsimpleapp.feature.user_details

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.grodno.vasili.githubsimpleapp.R
import by.grodno.vasili.githubsimpleapp.databinding.ActivityUserDetailsBinding
import by.grodno.vasili.githubsimpleapp.feature.base.BaseActivity
import timber.log.Timber

/**
 * Activity witch represent Detail info for User.
 */
class UserDetailsActivity : BaseActivity<ActivityUserDetailsBinding>() {
    companion object {
        const val LOGIN_KEY = "by.grodno.vasili.githubsimpleapp.feature.user_details.EXTRA.LOGIN"
    }

    private lateinit var model: UserDetailsViewModel
    private lateinit var dependencies: UserDetailsDependenciesModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dependencies = UserDetailsDependenciesModule()
        model = ViewModelProvider(this, dependencies.factory).get(UserDetailsViewModel::class.java)
        loadUserAsync()
        model.liveData.observe(this, { user: UserDetailsItem? -> binding.user = user })
    }

    private fun loadUserAsync() {
        val login = intent.getStringExtra(LOGIN_KEY)
        if (login == null) {
            Timber.w("Error taking extra with user login from intent")
            showToast("Sorry, we can`t show you this user details")
        } else {
            model.loadUserAsync(login)
        }
    }

    override val contentView = R.layout.activity_user_details
}
