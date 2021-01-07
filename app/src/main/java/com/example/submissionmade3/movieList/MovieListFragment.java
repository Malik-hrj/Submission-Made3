package com.example.submissionmade3.movieList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submissionmade3.R;
import com.example.submissionmade3.movieList.movieDetail.MovieDetail;
import com.example.submissionmade3.movieList.pojo.ResponseMovie;
import com.example.submissionmade3.movieList.pojo.ResultsItem;


public class MovieListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;

    private Observer<ResponseMovie> getMovies = new Observer<ResponseMovie>() {
        @Override
        public void onChanged(ResponseMovie responseMalik) {
            if (responseMalik !=null){
                if (responseMalik.getAnError()==null){
                    MovieListAdapter malikAdapter = new MovieListAdapter(responseMalik.getResults());
                    malikAdapter.setOnItemClickListener(new MovieListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent gotodetailMovie = new Intent(view.getContext(), MovieDetail.class);
                            gotodetailMovie.putExtra(MovieDetail.SELECTED_MOVIE,model);
                            startActivity(gotodetailMovie);
                        }
                    });
                    recyclerView.setAdapter(malikAdapter);
                }else{
                    alertDialog.setMessage(responseMalik.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_list_fragment,container,false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.viewRecycler);
        progressBar = view.findViewById(R.id.barProgress);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        }).create();

        LinearLayoutManager layoutManagerMalik = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManagerMalik);
        recyclerView.setHasFixedSize(true);
        MovieListViewModel malikkViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        malikkViewModel.doRequestListMovies();
        malikkViewModel.getMovies().observe(this,getMovies);

    }
}
