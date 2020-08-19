package by.grodno.vasili.githubsimpleapp.feature.users

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import by.grodno.vasili.githubsimpleapp.R
import by.grodno.vasili.githubsimpleapp.databinding.ActivityUsersBinding
import by.grodno.vasili.githubsimpleapp.feature.base.BaseActivity
import by.grodno.vasili.githubsimpleapp.feature.user_details.UserDetailsActivity
import timber.log.Timber

/**
 * Activity represent list of Users
 */
class UsersActivity : BaseActivity<ActivityUsersBinding>() {
    private lateinit var model: UsersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dependencies = UsersDependenciesModule(lifecycleScope)
        val adapter = dependencies.adapter
        model = ViewModelProvider(this, dependencies.factory).get(UsersViewModel::class.java)
        initRecyclerView(adapter)
        initPullToRefresh()
        model.pagedListLiveData.observe(this, stopRefreshAndSubmitPageList(adapter))
    }

    /**
     * Handler for click on recycler view item
     *
     * @param login Login of user in recycler view item
     */
    fun onItemClick(login: String?) {
        if (login == null) {
            showToast("Sorry, we can`t show you this user details")
            Timber.e("Empty login in recycler view")
            return
        }
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(UserDetailsActivity.LOGIN_KEY, login)
        startActivity(intent)
    }

    override val contentView = R.layout.activity_users

    private fun initRecyclerView(adapter: UsersAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun initPullToRefresh() {
        binding.refreshContainer.setOnRefreshListener { model.invalidateDatasource() }
    }

    private fun stopRefreshAndSubmitPageList(adapter: UsersAdapter): Observer<PagedList<UserItem>> {
        return Observer { pagedList: PagedList<UserItem> ->
            binding.refreshContainer.isRefreshing = false
            adapter.submitList(pagedList)
        }
    }
}