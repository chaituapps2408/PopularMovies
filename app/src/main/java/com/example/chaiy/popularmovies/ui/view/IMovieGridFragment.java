package com.example.chaiy.popularmovies.ui.view;

import com.example.chaiy.popularmovies.events.NetworkResponseListener;
import com.example.chaiy.popularmovies.model.MovieItemModel;

/**
 * Created by Chaiy on 7/8/2016.
 */
public interface IMovieGridFragment extends NetworkResponseListener{

    void displayMovieDetails(MovieItemModel movieItemModel);

}
