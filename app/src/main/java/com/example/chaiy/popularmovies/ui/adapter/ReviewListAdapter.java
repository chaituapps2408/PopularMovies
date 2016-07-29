package com.example.chaiy.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.model.ReviewItemModel;
import com.example.chaiy.popularmovies.presenter.MovieDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaiy on 7/23/2016.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListViewHolder> implements OnRecyclerViewItemClickedListener {

    private List<ReviewItemModel> reviewItemModelList;
    Context mContext;
    private MovieDetailsPresenter presenter;


    public ReviewListAdapter(Context mContext, MovieDetailsPresenter presenter) {
        reviewItemModelList = new ArrayList<>();
        this.mContext = mContext;
        this.presenter = presenter;
    }

    public void setData(List<ReviewItemModel> movieList) {
        this.reviewItemModelList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public ReviewListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        ReviewListViewHolder viewHolder = new ReviewListViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewListViewHolder holder, int position) {

        ReviewItemModel itemModel = reviewItemModelList.get(position);

        holder.userName.setText(itemModel.getAuthor());
        holder.review.setText(itemModel.getContent());
        //holder.readMore.setText(itemModel.getUrl());

    }

    @Override
    public int getItemCount() {
        return reviewItemModelList.size();
    }

    @Override
    public void onClick(View view, int position) {

        presenter.openURL(reviewItemModelList.get(position).getUrl());
    }
}
