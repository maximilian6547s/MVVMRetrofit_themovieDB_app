package com.example.mvvmretrofitdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmretrofitdemo.R;
import com.example.mvvmretrofitdemo.model.Result;
import com.example.mvvmretrofitdemo.view.MovieDetailsActivity;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private Context context;
    private ArrayList<Result> results;
//    private OnMovieClickListener onMovieClickListener;

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        Result result = results.get(position);
        if (result != null) {
            holder.textViewTitle.setText(result.getOriginalTitle());
            holder.popularityViewTitle.setText(Double.toString(result.getPopularity()));
            String imagePath = context.getString(R.string.poster_base_path) + result.getPosterPath();
            Glide.with(context).load(imagePath).placeholder(R.drawable.progress_circle).into(holder.imageViewPoster);

        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public ResultAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewPoster;
        private TextView textViewTitle;
        private TextView popularityViewTitle;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            popularityViewTitle = itemView.findViewById(R.id.popularityViewTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (onMovieClickListener == null) {
//                        onMovieClickListener.onMovieClick();
//                    }
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Result result = results.get(position);
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
//    public interface OnMovieClickListener {
//        void onMovieClick();
//    }
}
