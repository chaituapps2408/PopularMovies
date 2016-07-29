package com.example.chaiy.popularmovies.converter;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.chaiy.popularmovies.data.MoviesContract;
import com.example.chaiy.popularmovies.model.TrailerItemModel;

import java.util.List;

/**
 * Created by Chaiy on 7/26/2016.
 */
public class TrailerConverter {

    private static final int COLUMN_ID_INDEX = 0;
    private static final int COLUMN_MOVIE_ID_INDEX = 1;
    private static final int COLUMN_NAME_INDEX = 2;
    private static final int COLUMN_KEY_INDEX = 3;

    private TrailerConverter() {
    }

    public static TrailerItemModel toModel(Cursor cursor) {

        TrailerItemModel trailerItemModel = new TrailerItemModel();
        trailerItemModel.setId(cursor.getString(cursor.getColumnIndex(MoviesContract.VideoEntry.ID)));
        //trailerItemModel.setMovieID(cursor.getString(cursor.getColumnIndex(MoviesContract.VideoEntry.COLUMN_MOVIE_ID)));
        trailerItemModel.setKey(cursor.getString(cursor.getColumnIndex(MoviesContract.VideoEntry.COLUMN_KEY)));
        trailerItemModel.setName(cursor.getString(cursor.getColumnIndex(MoviesContract.VideoEntry.COLUMN_NAME)));

        return trailerItemModel;
    }
    public static ContentValues toContentValues(TrailerItemModel trailer, long movieId) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.VideoEntry.ID, trailer.getId());
        values.put(MoviesContract.VideoEntry.COLUMN_MOVIE_ID, movieId);
        values.put(MoviesContract.VideoEntry.COLUMN_NAME, trailer.getName());
        values.put(MoviesContract.VideoEntry.COLUMN_KEY, trailer.getKey());
        return values;
    }

    public static ContentValues[] toContentValues(List<TrailerItemModel> trailers,long movieId) {
        ContentValues[] values = new ContentValues[trailers.size()];

        int index=0;
        for(TrailerItemModel review : trailers) {
            values[index] = toContentValues(review,movieId);
            index++;
        }

        return values;
    }
}
