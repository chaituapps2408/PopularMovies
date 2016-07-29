package com.example.chaiy.popularmovies.network.resource.movies;

import com.example.chaiy.popularmovies.model.MoviesListResponse;
import com.example.chaiy.popularmovies.model.ReviewListResponse;
import com.example.chaiy.popularmovies.model.TrailerListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Chaiy on 7/9/2016.
 */
public interface MovieService {

    @GET("movie/popular")
    Call<MoviesListResponse> fetchPopularMovies(@QueryMap Map<String, String> queryParams);

    @GET("movie/top_rated")
    Call<MoviesListResponse> fetchTopRatedMovies(@QueryMap Map<String, String> queryParams);

    @GET("movie/{id}/videos")
    Call<TrailerListResponse> fetchTrailers(@Path("id") int id, @QueryMap Map<String, String> queryParams);

    @GET("movie/{id}/reviews")
    Call<ReviewListResponse> fetchReviews(@Path("id") int id, @QueryMap Map<String, String> queryParams);

}
