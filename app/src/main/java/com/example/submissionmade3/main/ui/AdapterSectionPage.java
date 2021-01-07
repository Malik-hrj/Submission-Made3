package com.example.submissionmade3.main.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.submissionmade3.R;
import com.example.submissionmade3.movieList.MovieListFragment;
import com.example.submissionmade3.tvShowList.TvShowListFragment;

public class AdapterSectionPage extends FragmentPagerAdapter {

    @StringRes
    public static final int[] TAB_TITLE = new int[]{R.string.movies_tab, R.string.tv_show_tab};
    private final Context contextMalik;
    private MovieListFragment movieMalikListFragment;
    private TvShowListFragment tvShowMalikListFragment;

    public AdapterSectionPage(Context context,FragmentManager fm) {
        super(fm);
        contextMalik = context;
        movieMalikListFragment = movieMalikListFragment;
        tvShowMalikListFragment = tvShowMalikListFragment;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return movieMalikListFragment;
        } else {
            return tvShowMalikListFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return contextMalik.getResources().getString(TAB_TITLE[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
