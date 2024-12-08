package com.example.ghettoyelp.viewHolders.ViewAllUsers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.R;

import java.util.List;

public class ViewAllUsersAdapter extends RecyclerView.Adapter<ViewAllUsersAdapter.ViewUserViewHolder> {
    List<User> users;
    List<Review> reviews;
    Context context;

    public ViewAllUsersAdapter(Context context, List<User> users,List<Review> reviews){
        this.users = users;
        this.context = context;
        this.reviews = reviews;
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
        holder.reviews = reviews;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class ViewUserViewHolder extends RecyclerView.ViewHolder{
        TextView userNameTextView, reviewNumberTextView;
        Button button;
        User user;
        List<Review> reviews;

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
                    else{
                        userReviewListDialog();
                    }
                }
            });
        }

        public void userReviewListDialog(){
            UserReviewDialog reviewDialog = new UserReviewDialog(button.getContext(), reviews,user){
                @Override
                public void onCreate(Bundle savedInstanceState){
                    super.onCreate(savedInstanceState);
                }
            };
            reviewDialog.show();
        }


    }
}
