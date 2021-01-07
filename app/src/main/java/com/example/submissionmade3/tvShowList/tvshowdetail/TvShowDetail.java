package com.example.submissionmade3.tvShowList.tvshowdetail;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.submissionmade3.R;
import com.example.submissionmade3.databinding.ActivityMovieDetailBinding;
import com.example.submissionmade3.databinding.ActivityTvshowDetailBinding;
import com.example.submissionmade3.tvShowList.pojo.ResultsItem;

import java.util.Objects;

public class TvShowDetail extends AppCompatActivity {
    public static final String SELECTED_TV_SHOW = "selected tv show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TvShowDetailViewModel viewModel = ViewModelProviders.of(this).get(TvShowDetailViewModel.class);
        ActivityTvshowDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tvshow_detail);
        ResultsItem tvShowModel = getIntent().getParcelableExtra(SELECTED_TV_SHOW);
        Log.d("IDN", "TVShowModel"+ tvShowModel.getName());
        viewModel.setResultsItem(tvShowModel);
        binding.setModelview2(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+tvShowModel.getPosterPath()).into(binding.posterImgage);
        setTitle(viewModel.getResultsItem().getName());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
