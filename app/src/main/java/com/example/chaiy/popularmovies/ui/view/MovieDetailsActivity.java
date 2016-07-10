package com.example.chaiy.popularmovies.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.model.MovieItemModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetailsActivity extends AppCompatActivity {


    TextView originalTitle;
    TextView releaseDate;
    TextView rating;
    TextView overview;
    ImageView thumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setupViews();

        MovieItemModel movieItemModel = getIntent().getParcelableExtra(Constants.APPLICATION_CONSTANTS.BUNDLE_MOVIE_DETAILS);

        displayMovieDetails(movieItemModel);
    }


    private void setupViews() {

        originalTitle = (TextView) findViewById(R.id.originalTitle);
        releaseDate = (TextView) findViewById(R.id.releaseYear);
        rating = (TextView) findViewById(R.id.rating);
        overview = (TextView) findViewById(R.id.overview);
        thumbnail = (ImageView) findViewById(R.id.moviePoster);
    }

    private void displayMovieDetails(MovieItemModel itemModel) {

        if (itemModel != null) {

            originalTitle.setText(itemModel.getOriginalTitle());
            releaseDate.setText(getDate(itemModel.getReleaseDate()));
            rating.setText(String.format("%s/10", itemModel.getVoteAverage()));
            overview.setText(itemModel.getOverview());

            Picasso.with(this)
                    .load(Constants.HTTP.IMAGE_BASE_URL + Constants.IMAGE_PARAMS.POSTER_WIDTH_185 + itemModel.getPosterPath())
                    .into(thumbnail);

        }
    }

    private String getDate(String dateInputString) {

        String dateOutputString = "";
        String expectedPattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern, Locale.US);
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {

            Date date = formatter.parse(dateInputString);
            dateOutputString = targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            dateOutputString = dateInputString;
        }

        return dateOutputString;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
