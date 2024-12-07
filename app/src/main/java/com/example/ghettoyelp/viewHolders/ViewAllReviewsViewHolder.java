package com.example.ghettoyelp.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.R;

public class ViewAllReviewsViewHolder extends RecyclerView.ViewHolder {
    private final TextView gymLogViewItem;

    private ViewAllReviewsViewHolder(View gymLogView){
        super(gymLogView);
        // reference to item that will be generated
        gymLogViewItem = gymLogView.findViewById(R.id.recyclerItemTextview);
    }

    public void bind (String text){
        gymLogViewItem.setText(text);
    }

    // reference to the displacement
    static ViewAllReviewsViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gymlog_recycler_item, parent, false);
        return new ViewAllReviewsViewHolder(view);
    }

}
