package by.grodno.vasili.githubsimpleapp.feature.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import by.grodno.vasili.githubsimpleapp.R;
import by.grodno.vasili.githubsimpleapp.databinding.UserItemBinding;

/**
 * Adapter for paginated recycler view with users
 */
public class UsersAdapter extends PagedListAdapter<UserItem, UsersAdapter.ViewHolder> {

    UsersAdapter(DiffUtil.ItemCallback<UserItem> diffUtilCallback) {
        super(diffUtilCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserItem item = getItem(position);
        holder.binding.setUser(item);
        holder.binding.setHandler((UsersActivity) holder.itemView.getContext());
    }

    /**
     * ViewHolder class for Users Recycler view
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding binding;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
