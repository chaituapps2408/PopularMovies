package com.example.chaiy.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Chaiy on 7/25/2016.
 */
public class MoviesProvider extends ContentProvider {
    /**
     * Id to request the full list of movies.
     */
    public static final int MOVIE = 100;

    /**
     * Id to request the Movies by Id.
     */
    public static final int MOVIE_BY_ID = 101;

    /**
     * Id to request the Movies sorted by a criteria.
     */
    public static final int SORTED_MOVIE = 102;

    /**
     * Id to execute a database operation over a video.
     */
    public static final int VIDEO = 103;

    /**
     * Id to execute a database operation over a review.
     */
    public static final int REVIEW = 104;

    /**
     * Id to request the videos of a movie.
     */
    public static final int MOVIE_VIDEOS = 105;

    /**
     * Id to request the videos of a movie.
     */
    public static final int MOVIE_REVIEWS = 106;

    /**
     * Manages the data base.
     */
    private MoviesDbHelper dbHelper;

    /**
     * Uri matcher used for this provider.
     */
    private static final UriMatcher uriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        dbHelper = new MoviesDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor result;

        final int match = buildUriMatcher().match(uri);
        switch (match) {
            case MOVIE:
            case MOVIE_BY_ID:
                result = dbHelper.getReadableDatabase().query(MoviesContract.MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case VIDEO:
            case MOVIE_VIDEOS:
                result = dbHelper.getReadableDatabase().query(MoviesContract.VideoEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case REVIEW:
            case MOVIE_REVIEWS:
                result = dbHelper.getReadableDatabase().query(MoviesContract.ReviewEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return result;
    }

    @Override
    public String getType(Uri uri) {
        final int match = buildUriMatcher().match(uri);
        switch (match) {
            case MOVIE:
            case SORTED_MOVIE:
            case MOVIE_VIDEOS:
            case MOVIE_REVIEWS:
                return MoviesContract.MovieEntry.CONTENT_TYPE;
            case MOVIE_BY_ID:
                return MoviesContract.MovieEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);

        Uri result;

        switch (match) {
            case MOVIE: {
                long id = database.insert(MoviesContract.MovieEntry.TABLE_NAME, null, values);
                if (id > 0)
                    result = MoviesContract.MovieEntry.buildMovieUri(Long.toString(id));
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);

                break;
            }
            case VIDEO: {
                long id = database.insert(MoviesContract.VideoEntry.TABLE_NAME, null, values);
                if (id > 0)
                    result = MoviesContract.VideoEntry.buildVideoUri(Long.toString(id));
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);

                break;
            }
            case REVIEW: {
                long id = database.insert(MoviesContract.ReviewEntry.TABLE_NAME, null, values);
                if (id > 0)
                    result = MoviesContract.ReviewEntry.buildReviewUri(Long.toString(id));
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);

                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri" + uri);
        }
        database.close();
        getContext().getContentResolver().notifyChange(result, null);

        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);

        int rowsDeleted = 0;

        if (null == selection) selection = "1";

        switch (match) {
            case MOVIE: {
                rowsDeleted = database.delete(MoviesContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case VIDEO: {
                rowsDeleted = database.delete(MoviesContract.VideoEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case REVIEW: {
                rowsDeleted = database.delete(MoviesContract.ReviewEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri" + uri);
        }
        database.close();

        if (rowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);

        int rowsUpdated = 0;

        switch (match) {
            case MOVIE: {
                rowsUpdated = database.update(MoviesContract.MovieEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case VIDEO: {
                rowsUpdated = database.update(MoviesContract.VideoEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case REVIEW: {
                rowsUpdated = database.update(MoviesContract.ReviewEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri" + uri);
        }
        database.close();

        if (rowsUpdated != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);

        switch (match) {
            case MOVIE: {
                return insertBulkData(MoviesContract.MovieEntry.TABLE_NAME, uri, values, database);
            }
            case VIDEO: {
                return insertBulkData(MoviesContract.VideoEntry.TABLE_NAME, uri, values, database);
            }
            case REVIEW: {
                return insertBulkData(MoviesContract.ReviewEntry.TABLE_NAME, uri, values, database);
            }
            default:
                return super.bulkInsert(uri, values);
        }
    }

    private int insertBulkData(String table, Uri uri, ContentValues[] values, SQLiteDatabase database) {
        int rowsInserted = 0;
        database.beginTransaction();
        try {
            for (ContentValues value : values) {
                long id = database.insert(table, null, value);
                if (id != -1)
                    rowsInserted++;
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsInserted;
    }

    @Override
    public void shutdown() {
        dbHelper.close();
        super.shutdown();
    }

    /***
     * This UriMatcher will match each URI to the MOVIE and SORTED_MOVIE integer constants defined
     * above. UriMatcher to select the query to execute.
     *
     * @return UriMatcher to select the query to execute.
     */
    public static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.MOVIE_PATH, MOVIE);
        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.MOVIE_PATH + "/*", MOVIE_BY_ID);
        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.MOVIE_PATH + "/*/"+ MoviesContract.VIDEO_PATH, MOVIE_VIDEOS);
        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.MOVIE_PATH + "/*/"+ MoviesContract.REVIEW_PATH, MOVIE_REVIEWS);

        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.VIDEO_PATH, VIDEO);
        matcher.addURI(MoviesContract.CONTENT_AUTHORITY, MoviesContract.REVIEW_PATH, REVIEW);
        return matcher;
    }
}