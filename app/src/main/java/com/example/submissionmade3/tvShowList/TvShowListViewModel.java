package com.example.submissionmade3.tvShowList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.submissionmade3.KatalogFilm;
import com.example.submissionmade3.tvShowList.pojo.TvShowResponse;

public class TvShowListViewModel extends ViewModel {
    private MutableLiveData<TvShowResponse> tvShowResponseMalik = new MutableLiveData<>();
    private Object ANError;

    MutableLiveData getTvShowList() {
        if (tvShowResponseMalik == null) {
            doRequestListTvShow();
        }
        return tvShowResponseMalik;
    }

    public void doRequestListTvShow() {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/550?api")
                .addQueryParameter("api_key", KatalogFilm.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(TvShowResponse.class, new ParsedRequestListener<TvShowResponse>() {
                    @Override
                    public void onResponse(TvShowResponse responseMalik) {
                        tvShowResponseMalik.postValue(responseMalik);
                    }

                    @Override
                    public void onError(ANError malikError) {
                        tvShowResponseMalik.setValue(new TvShowResponse(malikError));
                    }
                });

    }


}
