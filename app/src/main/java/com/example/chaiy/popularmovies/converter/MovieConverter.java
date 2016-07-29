package com.example.chaiy.popularmovies.converter;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.chaiy.popularmovies.data.MoviesContract;
import com.example.chaiy.popularmovies.model.MovieItemModel;

/**
 * Created by Chaiy on 7/26/2016.
 */
public class MovieConverter {

    public static int COLUMN_ID_INDEX = 0;
    public static int COLUMN_BACKDROP_IMAGE_INDEX = 1;
    public static int COLUMN_POPULARITY_INDEX = 2;
    public static int COLUMN_POSTER_IMAGE_INDEX = 3;
    public static int COLUMN_RELEASE_DATE_INDEX = 4;
    public static int COLUMN_SYNOPSIS_INDEX = 5;
    public static int COLUMN_TITLE_INDEX = 6;
    public static int COLUMN_VOTE_AVERAGE_INDEX = 7;

    private MovieConverter() {
    }

    public static MovieItemModel toModel(Cursor cursor) {

        MovieItemModel movieItemModel = new MovieItemModel();
        movieItemModel.setId(cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry.ID)));
        movieItemModel.setTitle(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_TITLE)));
        movieItemModel.setPosterPath(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE)));
        movieItemModel.setOverview(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_SYNOPSIS)));
        movieItemModel.setPopularity(cursor.getFloat(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POPULARITY)));
        movieItemModel.setReleaseDate(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE)));
        movieItemModel.setBackdropPath(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE)));
        movieItemModel.setVoteAverage(cursor.getFloat(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE)));

        return movieItemModel;
    }


    public static ContentValues toContentValues(MovieItemModel movie) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.MovieEntry.ID, movie.getId());
        values.put(MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE, movie.getBackdropPath());
        values.put(MoviesContract.MovieEntry.COLUMN_POPULARITY, movie.getPopularity());
        values.put(MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE, movie.getPosterPath());
        values.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(MoviesContract.MovieEntry.COLUMN_SYNOPSIS, movie.getOverview());
        values.put(MoviesContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        return values;
    }

}
