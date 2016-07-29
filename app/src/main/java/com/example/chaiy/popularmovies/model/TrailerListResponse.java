package com.example.chaiy.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chaiy on 7/8/2016.
 */
public class TrailerListResponse extends NetworkResponse implements Parcelable {

    @SerializedName(value = "id")
    @Expose
    int id;

    @SerializedName(value = "results")
    @Expose
    List<TrailerItemModel> trailerList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TrailerItemModel> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<TrailerItemModel> trailerList) {
        this.trailerList = trailerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.id);
        dest.writeTypedList(this.trailerList);
    }

    public TrailerListResponse() {
    }

    protected TrailerListResponse(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.trailerList = in.createTypedArrayList(TrailerItemModel.CREATOR);
    }

    public static final Creator<TrailerListResponse> CREATOR = new Creator<TrailerListResponse>() {
        @Override
        public TrailerListResponse createFromParcel(Parcel source) {
            return new TrailerListResponse(source);
        }

        @Override
        public TrailerListResponse[] newArray(int size) {
            return new TrailerListResponse[size];
        }
    };
}
