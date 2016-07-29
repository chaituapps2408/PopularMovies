package com.example.chaiy.popularmovies.ui.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.helper.DateUtils;
import com.example.chaiy.popularmovies.helper.Utility;
import com.example.chaiy.popularmovies.model.MovieItemModel;
import com.example.chaiy.popularmovies.model.ReviewItemModel;
import com.example.chaiy.popularmovies.model.ReviewListResponse;
import com.example.chaiy.popularmovies.model.TrailerItemModel;
import com.example.chaiy.popularmovies.model.TrailerListResponse;
import com.example.chaiy.popularmovies.presenter.MovieDetailsPresenter;
import com.example.chaiy.popularmovies.services.FavouriteService;
import com.example.chaiy.popularmovies.ui.adapter.ReviewListAdapter;
import com.example.chaiy.popularmovies.ui.adapter.TrailerListAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Chaiy on 7/16/2016.
 */
public class MovieDetailsFragment extends Fragment implements IMovieDetailsFragment {

    TextView originalTitle;
    TextView releaseDate;
    TextView rating;
    TextView overview;
    ImageView thumbnail;

    MovieItemModel movieItemModel;

    RecyclerView trailerList;
    ContentLoadingProgressBar progressBar;

    MovieDetailsPresenter movieDetailsPresenter;
    TrailerListAdapter trailerListAdapter;

    ContentLoadingProgressBar reviewProgressBar;
    RecyclerView reviewList;
    ReviewListAdapter reviewListAdapter;

    RatingBar addFav;

    public static MovieDetailsFragment newInstance(MovieItemModel model) {

        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.APPLICATION_CONSTANTS.BUNDLE_MOVIE_DETAILS, model);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        movieDetailsPresenter = new MovieDetailsPresenter(this, getContext());
        trailerListAdapter = new TrailerListAdapter(getContext(), movieDetailsPresenter);

        reviewListAdapter = new ReviewListAdapter(getContext(), movieDetailsPresenter);

        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            movieItemModel = getArguments().getParcelable(Constants.APPLICATION_CONSTANTS.BUNDLE_MOVIE_DETAILS);
        }

        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        setupViews(rootView);

        if (movieItemModel != null) {
            displayMovieDetails();
            movieDetailsPresenter.fetchMovieDetails(movieItemModel.getId());
        }
        return rootView;
    }


    private void setupViews(View rootView) {

        originalTitle = (TextView) rootView.findViewById(R.id.originalTitle);
        releaseDate = (TextView) rootView.findViewById(R.id.releaseYear);
        rating = (TextView) rootView.findViewById(R.id.rating);
        overview = (TextView) rootView.findViewById(R.id.overview);
        thumbnail = (ImageView) rootView.findViewById(R.id.moviePoster);

        trailerList = (RecyclerView) rootView.findViewById(R.id.trailerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        trailerList.setLayoutManager(linearLayoutManager);
        trailerList.setAdapter(trailerListAdapter);

        progressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progressBar);
        reviewProgressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progressBarReviews);

        reviewList = (RecyclerView) rootView.findViewById(R.id.reviewPager);
        LinearLayoutManager linearLayoutManagerReviews = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        reviewList.setLayoutManager(linearLayoutManagerReviews);
        reviewList.setAdapter(reviewListAdapter);

        addFav = (RatingBar) rootView.findViewById(R.id.add_fav);

        addFav.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (addFav.getRating() == 0) {
                        addFav.setRating(1);
                    } else {
                        addFav.setRating(0);
                    }
                    saveFavourite();
                }
                return true;
            }
        });


    }


    private void saveFavourite() {
        Intent addOrDeleteFav = new Intent(getActivity(), FavouriteService.class);
        addOrDeleteFav.putExtra(FavouriteService.MOVIE_EXTRA_KEY, movieItemModel);
        getActivity().startService(addOrDeleteFav);
    }

    private void displayMovieDetails() {

        if (movieItemModel != null) {

            originalTitle.setText(movieItemModel.getTitle());
            releaseDate.setText(DateUtils.formatDate(movieItemModel.getReleaseDate(), DateUtils.FORMAT_YYYY_MM_DD, DateUtils.FORMAT_DD_MMM_YYYY));
            rating.setText(String.format("%s/10", Utility.roundOffTo2DecPlaces(movieItemModel.getVoteAverage())));
            overview.setText(movieItemModel.getOverview());

            Picasso.with(getContext())
                    .load(Constants.HTTP.IMAGE_BASE_URL + Constants.IMAGE_PARAMS.POSTER_WIDTH_185 + movieItemModel.getPosterPath())
                    .into(thumbnail);

        }
    }


    private void handleFetchTrailerError() {
        progressBar.setVisibility(View.GONE);
        trailerList.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.fetch_error, Toast.LENGTH_SHORT).show();
    }

    private void handleFetchReviewError() {
        reviewProgressBar.setVisibility(View.GONE);
        reviewList.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.fetch_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isFavouriteMovie(boolean isFavouriteMovie) {
        if (isFavouriteMovie)
            addFav.setRating(1);
        else
            addFav.setRating(0);
    }

    @Override
    public void fetchTrailerStarted() {

        progressBar.setVisibility(View.VISIBLE);
        trailerList.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onFetchTrailerSuccess(List<TrailerItemModel> trailerItemList) {

        if (trailerItemList == null || trailerItemList.isEmpty()) {
            handleFetchTrailerError();
            return;
        }
        movieItemModel.setTrailerList(trailerItemList);
        trailerListAdapter.setData(trailerItemList);
        progressBar.setVisibility(View.GONE);
        trailerList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFetchTrailerFailure(Call<TrailerListResponse> call, Throwable t) {
        handleFetchTrailerError();
    }

    @Override
    public void fetchReviewStarted() {

        reviewProgressBar.setVisibility(View.VISIBLE);
        reviewList.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onFetchReviewSuccess(List<ReviewItemModel> reviewItemList) {

        if (reviewItemList == null || reviewItemList.isEmpty()) {
            handleFetchReviewError();
            return;
        }
        movieItemModel.setReviewsList(reviewItemList);
        reviewListAdapter.setData(reviewItemList);
        reviewProgressBar.setVisibility(View.GONE);
        reviewList.setVisibility(View.VISIBLE);

    }

    @Override
    public void onFetchReviewFailure(Call<ReviewListResponse> call, Throwable t) {
        handleFetchReviewError();
    }

    @Override
    public void readReviewInBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);

    }

    @Override
    public void watchYoutubeVideo(String key) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.APPLICATION_CONSTANTS.YOUTUBE_APP_PATH + key));
            getActivity().startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.APPLICATION_CONSTANTS.YOUTUBE_BASE_URL + key));
            startActivity(intent);
        }
    }


}
