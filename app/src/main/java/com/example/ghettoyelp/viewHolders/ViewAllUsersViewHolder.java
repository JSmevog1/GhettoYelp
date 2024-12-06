package com.example.ghettoyelp.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.R;
import com.example.ghettoyelp.databinding.ActivityViewAllUsersBinding;

@Deprecated
public class ViewAllUsersViewHolder extends RecyclerView.ViewHolder {
    ActivityViewAllUsersBinding binding;
    private final TextView userViewItem;
    private ViewAllUsersViewHolder(View viewAllUsersView){
        super(viewAllUsersView);
        userViewItem = viewAllUsersView.findViewById(R.id.usernameItemTextView);
    }

    public void bind (String text){
        userViewItem.setText(text);
    }

    static ViewAllUsersViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_recycler_item, parent, false);
        return new ViewAllUsersViewHolder(view);
    }

}
