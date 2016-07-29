package com.example.chaiy.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class NetworkResponse implements Parcelable {

    @SerializedName(value = "total_results")
    @Expose
    int totalResults;

    @SerializedName(value = "total_pages")
    @Expose
    int totalPages;


    public NetworkResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalResults);
        dest.writeInt(this.totalPages);
    }

    protected NetworkResponse(Parcel in) {
        this.totalResults = in.readInt();
        this.totalPages = in.readInt();
    }

    public static final Creator<NetworkResponse> CREATOR = new Creator<NetworkResponse>() {
        @Override
        public NetworkResponse createFromParcel(Parcel source) {
            return new NetworkResponse(source);
        }

        @Override
        public NetworkResponse[] newArray(int size) {
            return new NetworkResponse[size];
        }
    };
}
