package com.example.submissionmade3.movieList.movieDetail;

import androidx.lifecycle.ViewModel;

import com.example.submissionmade3.movieList.pojo.ResultsItem;

public class MovieDetailViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }
}
