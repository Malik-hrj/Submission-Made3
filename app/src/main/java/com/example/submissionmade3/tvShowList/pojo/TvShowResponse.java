package com.example.submissionmade3.tvShowList.pojo;

import androidx.annotation.NonNull;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    private static ANError anError;

    public TvShowResponse(ANError anError) { this.anError = anError; }

    public TvShowResponse() {
    }

    public static ANError getAnError() {
        return anError;
    }

    @SerializedName("page")
    private int page;

    @SerializedName("total_page")
    private int totalPages;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("total_result")
    private int totalResults;


    public List<ResultsItem> getResults() {return results; }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseProjects{" +
                        "Page = '" + '\'' + totalPages + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        "total_results = '" + totalResults + '\'' +
                        "}";
    }

}
