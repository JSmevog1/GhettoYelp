package com.example.ghettoyelp.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.R;

public class ViewAllUsersViewHolder extends RecyclerView.ViewHolder {
    private final TextView userViewItem;
    private ViewAllUsersViewHolder(View viewAllUsersView){
        super(viewAllUsersView);
        userViewItem = viewAllUsersView.findViewById(R.id.userRecyclerItemTextView);
    }

    public void bind (String text){
        userViewItem.setText(text);
    }

    static ViewAllUsersViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user_recycler_item, parent, false);
        return new ViewAllUsersViewHolder(view);
    }
}
