package com.example.chaiy.popularmovies.presenter;

import android.content.Context;
import android.database.Cursor;

import com.example.chaiy.popularmovies.converter.ReviewConverter;
import com.example.chaiy.popularmovies.converter.TrailerConverter;
import com.example.chaiy.popularmovies.data.MoviesContract;
import com.example.chaiy.popularmovies.events.NetworkResponseListener;
import com.example.chaiy.popularmovies.model.ReviewItemModel;
import com.example.chaiy.popularmovies.model.ReviewListResponse;
import com.example.chaiy.popularmovies.model.TrailerItemModel;
import com.example.chaiy.popularmovies.model.TrailerListResponse;
import com.example.chaiy.popularmovies.ui.view.IMovieDetailsFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class MovieDetailsPresenter extends BasePresenter implements NetworkResponseListener {

    IMovieDetailsFragment iMovieDetailsFragment;
    Context mContext;

    public MovieDetailsPresenter(IMovieDetailsFragment iMovieDetailsFragment, Context context) {
        super(iMovieDetailsFragment);
        this.mContext = context;
        this.iMovieDetailsFragment = iMovieDetailsFragment;
    }


    public void fetchMovieDetails(int movieID) {

        boolean isAvailable = isFavAvailableInDb(movieID);
        iMovieDetailsFragment.isFavouriteMovie(isAvailable);
        fetchMovieTrailers(movieID, isAvailable);
        fetchMovieReviews(movieID, isAvailable);

    }

    public void fetchMovieTrailers(int movieID, boolean isFromDB) {

        iMovieDetailsFragment.fetchTrailerStarted();


        if (isFromDB) {
            iMovieDetailsFragment.onFetchTrailerSuccess(trailerFromDB(movieID));
            return;
        }

        Call<TrailerListResponse> responseCall = mNetworkManager.getMovies().fetchTrailers(movieID, mRequestParams.getQueryMap());
        Callback<TrailerListResponse> callback = new Callback<TrailerListResponse>() {
            @Override
            public void onResponse(Call<TrailerListResponse> call, Response<TrailerListResponse> response) {

                iMovieDetailsFragment.onFetchTrailerSuccess(response.body().getTrailerList());

            }

            @Override
            public void onFailure(Call<TrailerListResponse> call, Throwable t) {
                iMovieDetailsFragment.onFetchTrailerFailure(call, t);
            }
        };

        responseCall.enqueue(callback);
    }

    public void fetchMovieReviews(int movieID, boolean isFromDB) {

        iMovieDetailsFragment.fetchReviewStarted();

        if (isFromDB) {
            iMovieDetailsFragment.onFetchReviewSuccess(reviewsFromDB(movieID));
            return;
        }

        Call<ReviewListResponse> responseCall = mNetworkManager.getMovies().fetchReviews(movieID, mRequestParams.getQueryMap());
        Callback<ReviewListResponse> callback = new Callback<ReviewListResponse>() {
            @Override
            public void onResponse(Call<ReviewListResponse> call, Response<ReviewListResponse> response) {

                iMovieDetailsFragment.onFetchReviewSuccess(response.body().getReviewList());

            }

            @Override
            public void onFailure(Call<ReviewListResponse> call, Throwable t) {
                iMovieDetailsFragment.onFetchReviewFailure(call, t);
            }
        };

        responseCall.enqueue(callback);
    }

    private boolean isFavAvailableInDb(int movieID) {
        String[] projection = new String[]{MoviesContract.MovieEntry.ID};
        String selection = MoviesContract.MovieEntry.ID + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = "" + movieID;

        Cursor movieCursor = mContext.getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,
                projection, selection, selectionArgs, null);
        if (movieCursor != null)
            return movieCursor.moveToFirst();


        return false;
    }


    private ArrayList<ReviewItemModel> reviewsFromDB(int movieID) {
        final String[] REVIEW_COLUMNS = {
                MoviesContract.VideoEntry.ID,
                MoviesContract.VideoEntry.COLUMN_MOVIE_ID,
                MoviesContract.ReviewEntry.COLUMN_AUTHOR,
                MoviesContract.ReviewEntry.COLUMN_CONTENT,
                MoviesContract.ReviewEntry.COLUMN_URL
        };

        String selectionClause = MoviesContract.ReviewEntry.COLUMN_MOVIE_ID + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = Integer.toString(movieID);

        Cursor reviewCursor = mContext.getContentResolver().query(MoviesContract.ReviewEntry.buildMoviePathWithReviews(movieID), REVIEW_COLUMNS,
                selectionClause, selectionArgs, null);

        ArrayList<ReviewItemModel> reviews = new ArrayList<>();
        if (reviewCursor != null) {
            while (reviewCursor.moveToNext()) {
                reviews.add(ReviewConverter.toModel(reviewCursor));
            }
            reviewCursor.close();
        }
        return reviews;
    }

    private ArrayList<TrailerItemModel> trailerFromDB(int movieID) {
        final String[] TRAILER_COLUMNS = {
                MoviesContract.VideoEntry.ID,
                MoviesContract.VideoEntry.COLUMN_MOVIE_ID,
                MoviesContract.VideoEntry.COLUMN_KEY,
                MoviesContract.VideoEntry.COLUMN_NAME
        };

        String selectionClause = MoviesContract.ReviewEntry.COLUMN_MOVIE_ID + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = Integer.toString(movieID);

        Cursor trailerCursor = mContext.getContentResolver().query(MoviesContract.VideoEntry.buildMoviePathWithVideos(movieID), TRAILER_COLUMNS,
                selectionClause, selectionArgs, null);

        ArrayList<TrailerItemModel> trailers = new ArrayList<>();
        if (trailerCursor != null) {
            while (trailerCursor.moveToNext()) {
                trailers.add(TrailerConverter.toModel(trailerCursor));
            }
            trailerCursor.close();
        }
        return trailers;
    }

    public void openURL(String url) {

        iMovieDetailsFragment.readReviewInBrowser(url);

    }

    public void openTrailer(String key) {

        iMovieDetailsFragment.watchYoutubeVideo(key);

    }

}
