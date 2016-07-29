package com.example.chaiy.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chaiy on 7/8/2016.
 */
public class ReviewListResponse extends NetworkResponse implements Parcelable {

    @SerializedName(value = "id")
    @Expose
    int id;

    @SerializedName(value = "results")
    @Expose
    List<ReviewItemModel> reviewList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReviewItemModel> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewItemModel> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.id);
        dest.writeTypedList(this.reviewList);
    }

    public ReviewListResponse() {
    }

    protected ReviewListResponse(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.reviewList = in.createTypedArrayList(ReviewItemModel.CREATOR);
    }

    public static final Creator<ReviewListResponse> CREATOR = new Creator<ReviewListResponse>() {
        @Override
        public ReviewListResponse createFromParcel(Parcel source) {
            return new ReviewListResponse(source);
        }

        @Override
        public ReviewListResponse[] newArray(int size) {
            return new ReviewListResponse[size];
        }
    };
}
