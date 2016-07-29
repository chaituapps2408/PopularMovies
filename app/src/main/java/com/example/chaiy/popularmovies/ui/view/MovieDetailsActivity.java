package com.example.chaiy.popularmovies.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.model.MovieItemModel;

public class MovieDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState == null) {
            MovieItemModel movieItemModel = getIntent().getParcelableExtra(Constants.APPLICATION_CONSTANTS.BUNDLE_MOVIE_DETAILS);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.movie_details_container, MovieDetailsFragment.newInstance(movieItemModel), Constants.APPLICATION_CONSTANTS.DETAILS_FRAGMENT)
                    .commit();

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
