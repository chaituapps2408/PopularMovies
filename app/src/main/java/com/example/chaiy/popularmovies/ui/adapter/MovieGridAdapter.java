package com.example.chaiy.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.model.MovieItemModel;
import com.example.chaiy.popularmovies.presenter.MovieListPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaiy on 7/8/2016.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridViewHolder> implements OnRecyclerViewItemClickedListener {

    private List<MovieItemModel> movieList;
    private Context mContext;
    private MovieListPresenter presenter;

    public MovieGridAdapter(Context context, MovieListPresenter presenter) {
        this.movieList = new ArrayList<>();
        this.mContext = context;
        this.presenter = presenter;
    }

    public void setData(List<MovieItemModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MovieGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieGridViewHolder viewHolder = new MovieGridViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieGridViewHolder holder, int position) {

        MovieItemModel itemModel = movieList.get(position);
        Picasso.with(mContext).load(Constants.HTTP.IMAGE_BASE_URL + Constants.IMAGE_PARAMS.POSTER_WIDTH_185 + itemModel.getPosterPath())
                 .into(holder.movieImageView);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    private MovieItemModel getItemAtPosition(int position) {
        return movieList.get(position);
    }

    @Override
    public void onClick(View view, int position) {
        presenter.displayMovieDetails(getItemAtPosition(position),position);
    }
}

