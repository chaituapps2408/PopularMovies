package com.example.chaiy.popularmovies.services;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.converter.MovieConverter;
import com.example.chaiy.popularmovies.converter.ReviewConverter;
import com.example.chaiy.popularmovies.converter.TrailerConverter;
import com.example.chaiy.popularmovies.data.MoviesContract;
import com.example.chaiy.popularmovies.helper.Utility;
import com.example.chaiy.popularmovies.model.MovieItemModel;

/**
 * Created by Chaiy on 7/25/2016.
 */
public class FavouriteService extends IntentService {

    private static final String TAG = FavouriteService.class.getSimpleName();
    public static final String MOVIE_EXTRA_KEY = "extra_key";

    MovieItemModel movieItemModel;

    public FavouriteService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        movieItemModel = intent.getParcelableExtra(MOVIE_EXTRA_KEY);

        String[] projection = new String[]{MoviesContract.MovieEntry.ID};
        String selection = MoviesContract.MovieEntry.ID + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = "" + movieItemModel.getId();

        Cursor movieCursor = getApplicationContext().getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,
                projection, selection, selectionArgs, null);
        if (movieCursor != null) {
            if (!movieCursor.moveToFirst()) {

                getApplicationContext().getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI,
                        MovieConverter.toContentValues(movieItemModel));

                if (!Utility.IsNullOrEmpty(movieItemModel.getReviewsList())) {
                    getApplicationContext().getContentResolver().bulkInsert(MoviesContract.ReviewEntry.CONTENT_URI,
                            ReviewConverter.toContentValues(movieItemModel.getReviewsList(), movieItemModel.getId()));
                }

                if (!Utility.IsNullOrEmpty(movieItemModel.getTrailerList())) {
                    Log.d(TAG, "Saving videos");
                    getApplicationContext().getContentResolver().bulkInsert(MoviesContract.VideoEntry.CONTENT_URI,
                            TrailerConverter.toContentValues(movieItemModel.getTrailerList(), movieItemModel.getId()));
                }
                notifyFavUpdate(movieItemModel.getTitle()+getString(R.string.fav_add_message));
            } else {
                deleteFavourite();
            }

            movieCursor.close();

        }

    }

    private void deleteFavourite() {

        String selectionMovie = MoviesContract.MovieEntry.ID + "=?";
        String selectionReview = MoviesContract.ReviewEntry.COLUMN_MOVIE_ID + "=?";
        String selectionTrailer = MoviesContract.VideoEntry.COLUMN_MOVIE_ID + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = "" + movieItemModel.getId();

        int id = getApplicationContext().getContentResolver().delete(MoviesContract.MovieEntry.CONTENT_URI,
                selectionMovie, selectionArgs);

        if (id > -1) {
            getApplicationContext().getContentResolver().delete(MoviesContract.ReviewEntry.CONTENT_URI,
                    selectionReview, selectionArgs);
            getApplicationContext().getContentResolver().delete(MoviesContract.VideoEntry.CONTENT_URI,
                    selectionTrailer, selectionArgs);

            notifyFavUpdate(movieItemModel.getTitle()+getString(R.string.fav_remove_message));
        }

    }

    private void notifyFavUpdate(String text) {
        final String message = String.format(text, movieItemModel.getTitle());
        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
