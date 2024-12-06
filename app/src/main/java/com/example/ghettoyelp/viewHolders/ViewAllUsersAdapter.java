package com.example.ghettoyelp.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.ghettoyelp.Database.Entities.User;

public class ViewAllUsersAdapter extends ListAdapter<User, ViewAllUsersViewHolder> {
    public ViewAllUsersAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallBack){
        super(diffCallBack);
    }

    @NonNull
    @Override
    public ViewAllUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return ViewAllUsersViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllUsersViewHolder holder, int position) {
        User current = getItem(position);
        holder.bind(current.toString());
    }

    static class UserDiff extends DiffUtil.ItemCallback<User>{
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    }
}
