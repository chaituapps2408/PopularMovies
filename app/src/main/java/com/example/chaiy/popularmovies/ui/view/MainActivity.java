package com.example.chaiy.popularmovies.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.model.MovieItemModel;

public class MainActivity extends AppCompatActivity implements MovieGridFragment.Callback {


    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.movie_details_container) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, new MovieDetailsFragment(), Constants.APPLICATION_CONSTANTS.DETAILS_FRAGMENT)
                        .commit();
            }
        } else {
            mTwoPane = false;
            if (getSupportActionBar() != null)
                getSupportActionBar().setElevation(0f);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

        return true;
    }

    @Override
    public void onItemClicked(MovieItemModel itemModel) {
        if (mTwoPane) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_details_container, MovieDetailsFragment.newInstance(itemModel), Constants.APPLICATION_CONSTANTS.DETAILS_FRAGMENT)
                    .commit();

        } else {
            Intent detailsIntent = new Intent(this, MovieDetailsActivity.class);
            detailsIntent.putExtra(Constants.APPLICATION_CONSTANTS.BUNDLE_MOVIE_DETAILS, itemModel);
            startActivity(detailsIntent);
        }
    }

    @Override
    public void onLoadMovieDetailsInTwoPane(MovieItemModel itemModel) {
        if (mTwoPane) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_details_container, MovieDetailsFragment.newInstance(itemModel), Constants.APPLICATION_CONSTANTS.DETAILS_FRAGMENT)
                    .commit();

        }
    }
}
