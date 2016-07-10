package com.example.chaiy.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chaiy on 7/8/2016.
 */
public class MoviesListResponse extends NetworkResponse implements Parcelable {

    @SerializedName(value = "page")
    @Expose
    int currentPage;

    @SerializedName(value = "results")
    @Expose
    List<MovieItemModel> movieList;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<MovieItemModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieItemModel> movieList) {
        this.movieList = movieList;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.currentPage);
        dest.writeTypedList(this.movieList);
        dest.writeInt(this.totalResults);
        dest.writeInt(this.totalPages);
    }


    protected MoviesListResponse(Parcel in) {
        this.currentPage = in.readInt();
        this.movieList = in.createTypedArrayList(MovieItemModel.CREATOR);
        this.totalResults = in.readInt();
        this.totalPages = in.readInt();
    }

    public static final Parcelable.Creator<MoviesListResponse> CREATOR = new Parcelable.Creator<MoviesListResponse>() {
        @Override
        public MoviesListResponse createFromParcel(Parcel source) {
            return new MoviesListResponse(source);
        }

        @Override
        public MoviesListResponse[] newArray(int size) {
            return new MoviesListResponse[size];
        }
    };
}
