package com.example.chaiy.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chaiy on 7/25/2016.
 */
public class MoviesDbHelper extends SQLiteOpenHelper {
    /**
     * Scheme version of the data base.
     */
    private static final int DATABASE_VERSION = 2;

    /**
     * Name of the database.
     */
    public static final String DATABASE_NAME = "favourite_movies.db";

    /**
     * Allows to create an instance of this helper to manage the creation of the database for popular
     * movie data.
     *
     * @param context Context of the application.
     */
    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MoviesContract.MovieEntry.TABLE_NAME + " (" +
                MoviesContract.MovieEntry.ID + " TEXT PRIMARY KEY," +
                MoviesContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_SYNOPSIS + " TEXT NULL," +
                MoviesContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_POPULARITY + " REAL NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE + " TEXT NULL," +
                MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE + " TEXT NULL);";

        final String SQL_CREATE_VIDEO_TABLE = "CREATE TABLE " + MoviesContract.VideoEntry.TABLE_NAME + " (" +
                MoviesContract.VideoEntry.ID + " TEXT PRIMARY KEY," +
                MoviesContract.VideoEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL," +
                MoviesContract.VideoEntry.COLUMN_NAME + " TEXT NOT NULL," +
                MoviesContract.VideoEntry.COLUMN_KEY + " TEXT NOT NULL);";

        final String SQL_CREATE_REVIEW_TABLE = "CREATE TABLE " + MoviesContract.ReviewEntry.TABLE_NAME + " (" +
                MoviesContract.ReviewEntry.ID + " TEXT PRIMARY KEY," +
                MoviesContract.ReviewEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL," +
                MoviesContract.ReviewEntry.COLUMN_AUTHOR + " TEXT NOT NULL," +
                MoviesContract.ReviewEntry.COLUMN_URL + " TEXT NOT NULL," +
                MoviesContract.ReviewEntry.COLUMN_CONTENT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_VIDEO_TABLE);
        db.execSQL(SQL_CREATE_REVIEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // We don't need to keep data on schema update
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.VideoEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.ReviewEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}