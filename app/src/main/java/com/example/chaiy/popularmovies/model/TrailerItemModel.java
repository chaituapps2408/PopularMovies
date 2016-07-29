package com.example.chaiy.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class TrailerItemModel implements Parcelable {

    @SerializedName(value = "id")
    @Expose
    String id;

    @SerializedName(value = "iso_639_1")
    @Expose
    String iso_639_1;

    @SerializedName(value = "iso_3166_1")
    @Expose
    String iso_3166_1;

    @SerializedName(value = "key")
    @Expose
    String key;

    @SerializedName(value = "name")
    @Expose
    String name;

    @SerializedName(value = "site")
    @Expose
    String site;

    @SerializedName(value = "size")
    @Expose
    int size;

    @SerializedName(value = "type")
    @Expose
    String type;


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

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.iso_639_1);
        dest.writeString(this.iso_3166_1);
        dest.writeString(this.key);
        dest.writeString(this.name);
        dest.writeString(this.site);
        dest.writeInt(this.size);
        dest.writeString(this.type);
    }

    public TrailerItemModel() {
    }

    protected TrailerItemModel(Parcel in) {
        this.id = in.readString();
        this.iso_639_1 = in.readString();
        this.iso_3166_1 = in.readString();
        this.key = in.readString();
        this.name = in.readString();
        this.site = in.readString();
        this.size = in.readInt();
        this.type = in.readString();
    }

    public static final Creator<TrailerItemModel> CREATOR = new Creator<TrailerItemModel>() {
        @Override
        public TrailerItemModel createFromParcel(Parcel source) {
            return new TrailerItemModel(source);
        }

        @Override
        public TrailerItemModel[] newArray(int size) {
            return new TrailerItemModel[size];
        }
    };
}
