package com.example.ghettoyelp.viewHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.R;

import java.util.ArrayList;

public class ViewAllUsersAdapter extends RecyclerView.Adapter<ViewAllUsersAdapter.MyViewHolder> {
    ArrayList<User> users;
    Context context;
    public ViewAllUsersAdapter(Context context, ArrayList<User> users){
        this.users = users;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_user_recycler_item, parent, false);
        return new ViewAllUsersAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewName.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.userRecyclerItemTextView);
        }
    }
}
