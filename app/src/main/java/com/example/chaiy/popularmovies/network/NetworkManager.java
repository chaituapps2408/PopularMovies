package com.example.chaiy.popularmovies.network;

import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.network.resource.movies.MovieService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class NetworkManager {


    MovieService mMoviesService;

    public MovieService getMovies() {

        if (mMoviesService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mMoviesService = retrofit.create(MovieService.class);

        }

        return mMoviesService;
    }


}
