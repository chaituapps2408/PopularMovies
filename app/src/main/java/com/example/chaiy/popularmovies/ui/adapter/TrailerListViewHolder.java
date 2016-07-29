package com.example.chaiy.popularmovies.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.chaiy.popularmovies.R;

/**
 * Created by Chaiy on 7/8/2016.
 */
public class TrailerListViewHolder extends RecyclerView.ViewHolder {

    ImageView trailerImageView;

    public TrailerListViewHolder(View itemView, final OnRecyclerViewItemClickedListener listener) {
        super(itemView);
        trailerImageView = (ImageView) itemView.findViewById(R.id.trailerView);
        trailerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, getAdapterPosition());
            }
        });
    }

}