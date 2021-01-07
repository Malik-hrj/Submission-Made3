package com.example.submissionmade3.tvShowList.tvshowdetail;

import androidx.lifecycle.ViewModel;

import com.example.submissionmade3.tvShowList.pojo.ResultsItem;

public class TvShowDetailViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() { return resultsItem; }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }

}
