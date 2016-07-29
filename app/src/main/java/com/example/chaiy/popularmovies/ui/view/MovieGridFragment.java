package com.example.chaiy.popularmovies.ui.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.chaiy.popularmovies.R;
import com.example.chaiy.popularmovies.helper.Constants;
import com.example.chaiy.popularmovies.helper.SharedPreferenceUtil;
import com.example.chaiy.popularmovies.helper.Utility;
import com.example.chaiy.popularmovies.model.MovieItemModel;
import com.example.chaiy.popularmovies.model.MoviesListResponse;
import com.example.chaiy.popularmovies.presenter.MovieListPresenter;
import com.example.chaiy.popularmovies.ui.adapter.MovieGridAdapter;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Chaiy on 7/8/2016.
 */
public class MovieGridFragment extends Fragment implements IMovieGridFragment, AdapterView.OnItemSelectedListener {

    private static final String TAG = MovieGridFragment.class.getSimpleName();
    private static final String PAGE_INDEX = "1";

    MovieListPresenter presenter;

    RecyclerView movieGrid;
    MovieGridAdapter movieGridAdapter;
    ProgressBar progressBar;

    SharedPreferences preferences;

    int selectedPosition = 0;

    public static MovieGridFragment newInstance() {

        MovieGridFragment fragment = new MovieGridFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        presenter = new MovieListPresenter(this, getContext());
        movieGridAdapter = new MovieGridAdapter(getContext(), presenter);

        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.menuSort);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        SpinnerAdapter adapter =
                ArrayAdapter.createFromResource(getContext(), R.array.sort_order,
                        android.R.layout.simple_spinner_dropdown_item);
        int index = SharedPreferenceUtil.getSortPreference(getContext());
        spinner.setAdapter(adapter);
        if (index <= adapter.getCount())
            spinner.setSelection(index);
        spinner.setSaveEnabled(true);
        spinner.setOnItemSelectedListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.movie_grid_fragment, null);
        progressBar = (ProgressBar) fragmentView.findViewById(R.id.progressBar);

        movieGrid = (RecyclerView) fragmentView.findViewById(R.id.movieGrid);
        movieGrid.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.movies_per_row)));

        movieGrid.setAdapter(movieGridAdapter);
        if (savedInstanceState != null) {
            selectedPosition = savedInstanceState.getInt(Constants.APPLICATION_CONSTANTS.SELECTED_POSITION);
        }
        return fragmentView;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (selectedPosition != RecyclerView.NO_POSITION) {
            outState.putInt(Constants.APPLICATION_CONSTANTS.SELECTED_POSITION, selectedPosition);
        }
        super.onSaveInstanceState(outState);
    }

    private void displayError() {
        progressBar.setVisibility(View.GONE);
        movieGrid.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovieDetails(MovieItemModel movieItemModel, int selectedPosition) {
        this.selectedPosition = selectedPosition;
        loadMovieDetails(movieItemModel);
    }

    private void loadMovieDetails(MovieItemModel movieItemModel) {
        ((Callback) getActivity()).onItemClicked(movieItemModel);
        movieGrid.smoothScrollToPosition(selectedPosition);
    }

    private void loadMovieDetailsInTwoPane(MovieItemModel movieItemModel) {
        ((Callback) getActivity()).onLoadMovieDetailsInTwoPane(movieItemModel);
        movieGrid.smoothScrollToPosition(selectedPosition);
    }

    @Override
    public void fetchStarted() {

        movieGrid.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<MovieItemModel> movies) {

        if (Utility.IsNullOrEmpty(movies)) {
            displayError();
            return;
        }
        movieGridAdapter.setData(movies);

        movieGrid.setVisibility(View.VISIBLE);
        if (selectedPosition >= movies.size()) {
            selectedPosition = 0;
        }
        loadMovieDetailsInTwoPane(movies.get(selectedPosition));
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onFailure(Call<MoviesListResponse> call, Throwable t) {
        Log.e(TAG, " error while fetching movies", t);
        movieGrid.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        displayError();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 1) {
            SharedPreferenceUtil.saveSortPreference(getContext(), i);
            presenter.fetchPopularMovies(PAGE_INDEX);
        } else if (i == 2) {
            SharedPreferenceUtil.saveSortPreference(getContext(), i);
            presenter.fetchTopRatedMovies(PAGE_INDEX);
        } else if (i == 3) {
            SharedPreferenceUtil.saveSortPreference(getContext(), i);
            presenter.fetchFavouriteMovies(PAGE_INDEX);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    interface Callback {
        void onItemClicked(MovieItemModel itemModel);

        void onLoadMovieDetailsInTwoPane(MovieItemModel itemModel);
    }

}
