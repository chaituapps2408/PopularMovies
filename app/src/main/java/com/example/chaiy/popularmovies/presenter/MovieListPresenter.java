package com.example.chaiy.popularmovies.presenter;

import android.content.Context;
import android.database.Cursor;

import com.example.chaiy.popularmovies.converter.MovieConverter;
import com.example.chaiy.popularmovies.data.MoviesContract;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.model.MovieItemModel;
import com.example.chaiy.popularmovies.model.MoviesListResponse;
import com.example.chaiy.popularmovies.ui.view.IMovieGridFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class MovieListPresenter extends BasePresenter {

    IMovieGridFragment iMovieGridFragment;
    Context mContext;

    public MovieListPresenter(IMovieGridFragment iMovieGridFragment, Context mContext) {
        super(iMovieGridFragment);
        this.mContext = mContext;
        this.iMovieGridFragment = iMovieGridFragment;
    }

    public void displayMovieDetails(MovieItemModel itemModel, int selectedPosition) {
        iMovieGridFragment.displayMovieDetails(itemModel, selectedPosition);
    }

    public void fetchTopRatedMovies(String pages) {

        iMovieGridFragment.fetchStarted();

        mRequestParams.addQueryParam(Constants.REQUEST_PARAMS.NO_OF_PAGES, pages);

        Call<MoviesListResponse> responseCall = mNetworkManager.getMovies().fetchTopRatedMovies(mRequestParams.getQueryMap());
        Callback<MoviesListResponse> callback = new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {

                iMovieGridFragment.onSuccess(response.body().getMovieList());

            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                iMovieGridFragment.onFailure(call, t);
            }
        };

        responseCall.enqueue(callback);

    }


    public void fetchPopularMovies(String pages) {

        iMovieGridFragment.fetchStarted();

        mRequestParams.addQueryParam(Constants.REQUEST_PARAMS.NO_OF_PAGES, pages);

        Call<MoviesListResponse> responseCall = mNetworkManager.getMovies().fetchPopularMovies(mRequestParams.getQueryMap());
        Callback<MoviesListResponse> callback = new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {

                iMovieGridFragment.onSuccess(response.body().getMovieList());

            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                iMovieGridFragment.onFailure(call, t);
            }
        };

        responseCall.enqueue(callback);

    }

    public void fetchFavouriteMovies(String pages) {

        iMovieGridFragment.fetchStarted();
        final String[] MOVIE_COLUMNS = {
                MoviesContract.MovieEntry.ID,
                MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE,
                MoviesContract.MovieEntry.COLUMN_POPULARITY,
                MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE,
                MoviesContract.MovieEntry.COLUMN_RELEASE_DATE,
                MoviesContract.MovieEntry.COLUMN_SYNOPSIS,
                MoviesContract.MovieEntry.COLUMN_TITLE,
                MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE
        };

        Cursor movieCursor = mContext.getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI, MOVIE_COLUMNS, null, null, null);

        ArrayList<MovieItemModel> movies = new ArrayList<>();
        while (movieCursor.moveToNext()) {
            MovieItemModel movie = MovieConverter.toModel(movieCursor);
            movie.setFavourite(true);
            movies.add(movie);
        }
        movieCursor.close();

        iMovieGridFragment.onSuccess(movies);
    }
}
