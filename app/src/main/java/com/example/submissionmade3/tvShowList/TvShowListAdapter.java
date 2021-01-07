package com.example.submissionmade3.tvShowList;

import android.annotation.SuppressLint;
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
import com.example.submissionmade3.tvShowList.pojo.ResultsItem;

import java.util.List;

public class TvShowListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResultsItem> itemList;

    private OnItemClickListener malikItemClickListener;

    TvShowListAdapter(List<ResultsItem> itemList) { this.itemList = itemList; }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movie, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetText18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ResultsItem model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.textTitleItem.setText(model.getName());

            if (model.getOverview().length()>50){
                genericViewHolder.textOverViewItem.setText(model.getOverview().substring(0,49)+".....");
            }else {
                genericViewHolder.textOverViewItem.setText(model.getOverview());
            }

            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w185"+model.getPosterPath()).into(genericViewHolder.posterimg);
        }

    }

    @Override
    public int getItemCount() { return itemList.size(); }

    void setOnItemClickListener(final OnItemClickListener malikItemClickListener) {
        this.malikItemClickListener = malikItemClickListener;
    }

    private ResultsItem getItem(int position) { return itemList.get(position); }

    public interface OnItemClickListener {
        void onItemClick(View view, ResultsItem model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView posterimg;
        private TextView textTitleItem;
        private TextView textOverViewItem;

        ViewHolder(final View itemView) {
            super(itemView);

            this.posterimg = itemView.findViewById(R.id.poster_img);
            this.textTitleItem = itemView.findViewById(R.id.txt_item_title);
            this.textOverViewItem = itemView.findViewById(R.id.text_over_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    malikItemClickListener.onItemClick(itemView, itemList.get(getAdapterPosition()));

                }
            });

        }
    }
}

