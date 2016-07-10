package com.example.chaiy.popularmovies.events;

import com.example.chaiy.popularmovies.model.NetworkResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Chaiy on 7/9/2016.
 */
public interface NetworkResponseListener <R extends NetworkResponse>{

    void fetchStarted();

    void onSuccess(Call<R> call, Response<R> response);

    void onFailure(Call<R> call, Throwable t);

}
