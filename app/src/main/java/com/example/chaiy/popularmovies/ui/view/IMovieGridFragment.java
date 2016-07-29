package com.example.chaiy.popularmovies.ui.view;

import com.example.chaiy.popularmovies.events.NetworkResponseListener;
import com.example.chaiy.popularmovies.model.MovieItemModel;
import com.example.chaiy.popularmovies.model.MoviesListResponse;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Chaiy on 7/8/2016.
 */
public interface IMovieGridFragment extends NetworkResponseListener{

    void fetchStarted();

    void onSuccess(List<MovieItemModel> movies);

    void onFailure(Call<MoviesListResponse> call, Throwable t);

    void displayMovieDetails(MovieItemModel movieItemModel,int selectedPosition);

}
