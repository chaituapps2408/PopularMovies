package com.example.chaiy.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.model.TrailerItemModel;
import com.example.chaiy.popularmovies.presenter.MovieDetailsPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaiy on 7/23/2016.
 */
public class TrailerListAdapter extends RecyclerView.Adapter<TrailerListViewHolder> implements OnRecyclerViewItemClickedListener {


    private List<TrailerItemModel> trailerItemModels;
    private Context mContext;
    private MovieDetailsPresenter presenter;

    public TrailerListAdapter(Context context, MovieDetailsPresenter presenter) {
        this.trailerItemModels = new ArrayList<>();
        this.mContext = context;
        this.presenter = presenter;
    }

    public void setData(List<TrailerItemModel> movieList) {
        this.trailerItemModels = movieList;
        notifyDataSetChanged();
    }

    @Override
    public TrailerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        TrailerListViewHolder viewHolder = new TrailerListViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerListViewHolder holder, int position) {

        TrailerItemModel itemModel = trailerItemModels.get(position);
        Picasso.with(mContext).load(Constants.HTTP.YOUTUBE_IMAGE_BASE_URL + itemModel.getKey() + Constants.IMAGE_PARAMS.YOUTUBE_THUMB_NUM_1)
                .into(holder.trailerImageView);

    }

    @Override
    public int getItemCount() {
        return trailerItemModels.size();
    }

    @Override
    public void onClick(View view, int position) {

        presenter.openTrailer(trailerItemModels.get(position).getKey());
    }
}
