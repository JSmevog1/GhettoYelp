package com.example.ghettoyelp.viewHolders.ViewAllUsers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.R;

import java.util.List;

public class ViewAllUsersAdapter extends RecyclerView.Adapter<ViewAllUsersAdapter.ViewUserViewHolder> {
    List<User> users;
    Context context;
    public ViewAllUsersAdapter(Context context, List<User> users){
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_recycler_item, parent, false);
        return new ViewUserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewUserViewHolder holder, int position) {
        User user = users.get(position);
        holder.userNameTextView.setText(user.getUsername());
        holder.reviewNumberTextView.setText("Reviews: " + user.getReviewsCount());
        holder.user = user;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class ViewUserViewHolder extends RecyclerView.ViewHolder{
        TextView userNameTextView, reviewNumberTextView;
        Button button;
        User user;

        public ViewUserViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.usernameItemTextView);
            reviewNumberTextView = itemView.findViewById(R.id.reviewsItemTextView);
            button = itemView.findViewById(R.id.userViewsButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user.getReviewsCount() == 0){
                        Toast toast = Toast.makeText(button.getContext(), user.getUsername() + " has no reviews.", Toast.LENGTH_SHORT );
                        toast.show();
                    }
                }
            });
        }
    }
}
