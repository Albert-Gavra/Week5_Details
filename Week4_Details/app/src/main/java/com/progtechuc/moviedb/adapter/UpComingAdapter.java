package com.progtechuc.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.progtechuc.moviedb.R;
import com.progtechuc.moviedb.helper.Const;
import com.progtechuc.moviedb.model.UpComing;
import com.progtechuc.moviedb.view.activities.MovieDetailsActivity;

import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.CardViewViewHolder>{

    private Context context;
    private List<UpComing.ResultsDTO> listUpComing;
    private List<UpComing.ResultsDTO> getListUpComing(){
        return listUpComing;
    }
    public void setListUpComing(List<UpComing.ResultsDTO> listUpComing){
        this.listUpComing = listUpComing;
    }
    public UpComingAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_now_playing, parent, false);
        return new UpComingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final UpComing.ResultsDTO results = getListUpComing().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_release_date.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.img_poster);
    }

    @Override
    public int getItemCount() { return getListUpComing().size(); }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cv;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_title = itemView.findViewById(R.id.lbl_title_card_upcoming);
            lbl_release_date = itemView.findViewById(R.id.lbl_releasedate_card_upcoming);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card_nowplaying);
            cv = itemView.findViewById(R.id.cv_card_upcoming);
        }
    }
}


