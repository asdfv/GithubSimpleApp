package by.grodno.vasili.githubsimpleapp.feature.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.grodno.vasili.githubsimpleapp.R
import by.grodno.vasili.githubsimpleapp.databinding.UserItemBinding

/**
 * Adapter for paginated recycler view with users
 */
class UsersAdapter(
        diffUtilCallback: DiffUtil.ItemCallback<UserItem>
) : PagedListAdapter<UserItem, UsersAdapter.ViewHolder>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding?.user = item
        holder.binding?.handler = holder.itemView.context as UsersActivity
    }

    /**
     * ViewHolder class for Users Recycler view
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: UserItemBinding? = DataBindingUtil.bind(itemView)
    }
}