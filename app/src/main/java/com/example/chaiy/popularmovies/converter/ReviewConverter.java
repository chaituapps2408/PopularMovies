package com.example.chaiy.popularmovies.converter;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.chaiy.popularmovies.data.MoviesContract;
import com.example.chaiy.popularmovies.model.ReviewItemModel;

import java.util.List;

/**
 * Created by Chaiy on 7/26/2016.
 */
public class ReviewConverter {

    private static final int COLUMN_ID_INDEX = 0;
    private static final int COLUMN_MOVIE_ID_INDEX = 1;
    private static final int COLUMN_AUTHOR_INDEX = 2;
    private static final int COLUMN_CONTENT_INDEX = 3;

    public static ReviewItemModel toModel(Cursor cursor) {
        ReviewItemModel model = new ReviewItemModel();
        model.setId(cursor.getString(cursor.getColumnIndex(MoviesContract.ReviewEntry.ID)));
        model.setAuthor(cursor.getString(cursor.getColumnIndex(MoviesContract.ReviewEntry.COLUMN_AUTHOR)));
        model.setContent(cursor.getString(cursor.getColumnIndex(MoviesContract.ReviewEntry.COLUMN_CONTENT)));
        model.setUrl(cursor.getString(cursor.getColumnIndex(MoviesContract.ReviewEntry.COLUMN_URL)));
        //model.setMovieID(cursor.getString(cursor.getColumnIndex(MoviesContract.ReviewEntry.ID)));

        return model;
    }

    public static ContentValues toContentValues(ReviewItemModel review, long movieId) {
        Log.d("Test", "" + review);
        ContentValues values = new ContentValues();
        values.put(MoviesContract.ReviewEntry.ID, review.getId());
        values.put(MoviesContract.ReviewEntry.COLUMN_MOVIE_ID, movieId);
        values.put(MoviesContract.ReviewEntry.COLUMN_AUTHOR, review.getAuthor());
        values.put(MoviesContract.ReviewEntry.COLUMN_CONTENT, review.getContent());
        values.put(MoviesContract.ReviewEntry.COLUMN_URL, review.getUrl());
        return values;
    }

    public static ContentValues[] toContentValues(List<ReviewItemModel> reviews, long movieId) {
        ContentValues[] values = new ContentValues[reviews.size()];

        int index = 0;
        for (ReviewItemModel review : reviews) {
            values[index] = toContentValues(review, movieId);
            index++;
        }

        return values;
    }
}
