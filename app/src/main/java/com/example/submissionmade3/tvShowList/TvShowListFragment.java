package com.example.submissionmade3.tvShowList;

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
import com.example.submissionmade3.tvShowList.pojo.ResultsItem;
import com.example.submissionmade3.tvShowList.pojo.TvShowResponse;
import com.example.submissionmade3.tvShowList.tvshowdetail.TvShowDetail;
import com.example.submissionmade3.tvShowList.tvshowdetail.TvShowDetailViewModel;

public class TvShowListFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;

    private Observer<TvShowResponse> getTvShow = new Observer<TvShowResponse>() {
        @Override
        public void onChanged(TvShowResponse tvShowResponse) {
            if (tvShowResponse!=null) {
                if (TvShowResponse.getAnError()==null) {
                    TvShowListAdapter adapterMalik = new TvShowListAdapter(tvShowResponse.getResults());
                    adapterMalik.setOnItemClickListener(new TvShowListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent goToDetailTvShow = new Intent(view.getContext(), TvShowDetail.class);
                            goToDetailTvShow.putExtra(TvShowDetail.SELECTED_TV_SHOW,model);
                            startActivity(goToDetailTvShow);
                        }
                    });

                    recyclerView.setAdapter(adapterMalik);
                }else{
                    alertDialog.setMessage(tvShowResponse.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tv_show_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.barProgress);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        TvShowListViewModel viewModelmalik = ViewModelProviders.of(this).get(TvShowListViewModel.class);
        viewModelmalik.doRequestListTvShow();
        viewModelmalik.getTvShowList().observe(this, getTvShow);
    }
}