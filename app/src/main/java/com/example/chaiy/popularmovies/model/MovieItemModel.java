package com.example.chaiy.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class MovieItemModel implements Parcelable {

    @SerializedName(value = "poster_path")
    @Expose
    String posterPath;

    @SerializedName(value = "adult")
    @Expose
    String adult;

    @SerializedName(value = "overview")
    @Expose
    String overview;

    @SerializedName(value = "release_date")
    @Expose
    String releaseDate;

    @SerializedName(value = "genre_ids")
    @Expose
    int[] genreIds;

    @SerializedName(value = "id")
    @Expose
    int id;

    @SerializedName(value = "original_title")
    @Expose
    String originalTitle;

    @SerializedName(value = "original_language")
    @Expose
    String originalLanguage;

    @SerializedName(value = "title")
    @Expose
    String title;

    @SerializedName(value = "backdrop_path")
    @Expose
    String backdropPath;

    @SerializedName(value = "popularity")
    @Expose
    String popularity;

    @SerializedName(value = "vote_count")
    @Expose
    int vote_count;

    @SerializedName(value = "video")
    @Expose
    boolean video;

    @SerializedName(value = "vote_average")
    @Expose
    double voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeString(this.adult);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeIntArray(this.genreIds);
        dest.writeInt(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeString(this.popularity);
        dest.writeInt(this.vote_count);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.voteAverage);
    }

    public MovieItemModel() {
    }

    protected MovieItemModel(Parcel in) {
        this.posterPath = in.readString();
        this.adult = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.genreIds = in.createIntArray();
        this.id = in.readInt();
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = in.readString();
        this.vote_count = in.readInt();
        this.video = in.readByte() != 0;
        this.voteAverage = in.readDouble();
    }

    public static final Parcelable.Creator<MovieItemModel> CREATOR = new Parcelable.Creator<MovieItemModel>() {
        @Override
        public MovieItemModel createFromParcel(Parcel source) {
            return new MovieItemModel(source);
        }

        @Override
        public MovieItemModel[] newArray(int size) {
            return new MovieItemModel[size];
        }
    };
}
