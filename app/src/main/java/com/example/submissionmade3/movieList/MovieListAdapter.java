package com.example.submissionmade3.movieList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submissionmade3.R;
import com.example.submissionmade3.movieList.pojo.ResultsItem;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResultsItem> itemlist;

    private OnItemClickListener itemClickListenerMalik;

    public MovieListAdapter(List<ResultsItem> itemList) { this.itemlist = itemList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_movie_detail,viewGroup,false);

        return new ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            final ResultsItem model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTitleText.setText(model.getTitle());

            if(model.getOverview().length()>50) {
                genericViewHolder.itemOverviewText.setText(model.getOverview().substring(0,49)+" ... ");
            }else{
                genericViewHolder.itemOverviewText.setText(model.getOverview());
            }

            Glide.with(holder.itemView.getContext()).load(""+model.getPosterPath()).into(genericViewHolder.photoImage);
        }

    }

    @Override
    public int getItemCount() { return itemlist.size(); }

    public void setOnItemClickListener(OnItemClickListener itemClickListenerMalik ) {
        this.itemClickListenerMalik = itemClickListenerMalik;
    }

    private ResultsItem getItem(int position) { return itemlist.get(position); }

    public interface OnItemClickListener {
        void onItemClick(View view, ResultsItem model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoImage;
        private TextView itemTitleText;
        private TextView itemOverviewText;

        ViewHolder(final View itemView) {
            super(itemView);

            this.photoImage = itemView.findViewById(R.id.poster_img);
            this.itemTitleText = itemView.findViewById(R.id.title_judul);
            this.itemOverviewText = itemView.findViewById(R.id.text_over_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListenerMalik.onItemClick(itemView, itemlist.get(getAdapterPosition()));

                }
            });
        }
    }
}

