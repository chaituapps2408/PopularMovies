package com.example.chaiy.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class ReviewItemModel implements Parcelable {

    @SerializedName(value = "id")
    @Expose
    String id;

    @SerializedName(value = "author")
    @Expose
    String author;

    @SerializedName(value = "content")
    @Expose
    String content;

    @SerializedName(value = "url")
    @Expose
    String url;

    String movieID;


    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

    public ReviewItemModel() {
    }

    protected ReviewItemModel(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Creator<ReviewItemModel> CREATOR = new Creator<ReviewItemModel>() {
        @Override
        public ReviewItemModel createFromParcel(Parcel source) {
            return new ReviewItemModel(source);
        }

        @Override
        public ReviewItemModel[] newArray(int size) {
            return new ReviewItemModel[size];
        }
    };
}
