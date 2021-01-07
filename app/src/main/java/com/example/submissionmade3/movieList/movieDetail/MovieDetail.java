package com.example.submissionmade3.movieList.movieDetail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.submissionmade3.R;
import com.example.submissionmade3.databinding.ActivityMovieDetailBinding;
import com.example.submissionmade3.movieList.pojo.ResultsItem;

import java.util.Objects;

import javax.xml.transform.Result;

public class MovieDetail extends AppCompatActivity {
    public static final String SELECTED_MOVIE = "selected_movies";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieDetailViewModel viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        ResultsItem ModelMovies = getIntent().getParcelableExtra(SELECTED_MOVIE);
        viewModel.setResultsItem(ModelMovies);
        binding.setModelview(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+ModelMovies.getPosterPath()).into(binding.posterimaggeshay);
        setTitle(viewModel.getResultsItem().getTitle());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
