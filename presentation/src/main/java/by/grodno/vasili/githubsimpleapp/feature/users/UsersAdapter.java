package by.grodno.vasili.githubsimpleapp.feature.users;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.grodno.vasili.githubsimpleapp.R;
import by.grodno.vasili.githubsimpleapp.databinding.UserItemBinding;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private List<UserItem> items;

    UsersAdapter() {
        this.items = new ArrayList<>();
    }

    void setItems(List<UserItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserItem item = items.get(position);
        holder.binding.setUser(item);
        holder.binding.setHandler((UsersActivity) holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return items.size();
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
