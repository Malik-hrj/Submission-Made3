package com.example.submissionmade3.movieList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.submissionmade3.KatalogFilm;
import com.example.submissionmade3.movieList.pojo.ResponseMovie;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<ResponseMovie> responsemalikk = new MutableLiveData<>();

    public MutableLiveData getMovies(){
        if (responsemalikk==null){
            doRequestListMovies();

        }
        return responsemalikk;
    }
    void doRequestListMovies() {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/550?api")
                .addQueryParameter("api_key", KatalogFilm.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseMovie.class, new ParsedRequestListener<ResponseMovie>() {
                    @Override
                    public void onResponse(ResponseMovie response) {
                        responsemalikk.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responsemalikk.setValue(new ResponseMovie(anError));
                    }
                });
    }

}
