package com.example.chaiy.popularmovies.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chaiy.popularmovies.R;

/**
 * Created by Chaiy on 7/8/2016.
 */
public class ReviewListViewHolder extends RecyclerView.ViewHolder {

    TextView userName;
    TextView review;
    TextView readMore;

    public ReviewListViewHolder(final View itemView, final OnRecyclerViewItemClickedListener listener) {
        super(itemView);
        userName = (TextView) itemView.findViewById(R.id.userName);
        review = (TextView) itemView.findViewById(R.id.review);
        readMore = (TextView) itemView.findViewById(R.id.readMore);

        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(itemView, getAdapterPosition());
            }
        });

    }

}