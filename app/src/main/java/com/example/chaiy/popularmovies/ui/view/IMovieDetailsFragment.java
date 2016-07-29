package com.example.chaiy.popularmovies.ui.view;

import com.example.chaiy.popularmovies.events.NetworkResponseListener;
import com.example.chaiy.popularmovies.model.ReviewItemModel;
import com.example.chaiy.popularmovies.model.ReviewListResponse;
import com.example.chaiy.popularmovies.model.TrailerItemModel;
import com.example.chaiy.popularmovies.model.TrailerListResponse;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Chaiy on 7/8/2016.
 */
public interface IMovieDetailsFragment extends NetworkResponseListener {

    void isFavouriteMovie(boolean isFavouriteMovie);

    void fetchTrailerStarted();

    void onFetchTrailerSuccess(List<TrailerItemModel> trailerList);

    void onFetchTrailerFailure(Call<TrailerListResponse> call, Throwable t);

    void fetchReviewStarted();

    void onFetchReviewSuccess(List<ReviewItemModel> reviewList);

    void onFetchReviewFailure(Call<ReviewListResponse> call, Throwable t);

    void watchYoutubeVideo(String key);

    void readReviewInBrowser(String url);

}
