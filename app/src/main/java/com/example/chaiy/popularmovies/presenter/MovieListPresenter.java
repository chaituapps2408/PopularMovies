package com.example.chaiy.popularmovies.presenter;

import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.model.MovieItemModel;
import com.example.chaiy.popularmovies.model.MoviesListResponse;
import com.example.chaiy.popularmovies.ui.view.IMovieGridFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class MovieListPresenter extends BasePresenter {

    IMovieGridFragment iMovieGridFragment;

    public MovieListPresenter(IMovieGridFragment iMovieGridFragment) {
        super(iMovieGridFragment);
        this.iMovieGridFragment = iMovieGridFragment;
    }

    public void displayMovieDetails(MovieItemModel itemModel){
        iMovieGridFragment.displayMovieDetails(itemModel);
    }
    public void fetchTopRatedMovies(String pages) {

        mRequestParams.addQueryParam(Constants.REQUEST_PARAMS.NO_OF_PAGES, pages);

        Call<MoviesListResponse> responseCall = mNetworkManager.getMovies().fetchTopRatedMovies(mRequestParams.getQueryMap());
        Callback<MoviesListResponse> callback = new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {

                responseListener.onSuccess(call, response);

            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                responseListener.onFailure(call, t);
            }
        };

        responseCall.enqueue(callback);

    }

    public void fetchPopularMovies(String pages) {

        mRequestParams.addQueryParam(Constants.REQUEST_PARAMS.NO_OF_PAGES, pages);

        Call<MoviesListResponse> responseCall = mNetworkManager.getMovies().fetchPopularMovies(mRequestParams.getQueryMap());
        Callback<MoviesListResponse> callback = new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {

                responseListener.onSuccess(call, response);

            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                responseListener.onFailure(call, t);
            }
        };

        responseCall.enqueue(callback);

    }
}
