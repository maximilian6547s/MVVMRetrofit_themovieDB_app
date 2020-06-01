package com.example.mvvmretrofitdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmretrofitdemo.R;
import com.example.mvvmretrofitdemo.databinding.ResultItemBinding;
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

        ResultItemBinding resultItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.result_item,parent,false);
        return new ResultViewHolder(resultItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        Result result = results.get(position);
        if (result != null) {
//            String imagePath = context.getString(R.string.poster_base_path) + result.getPosterPath();
//            result.setPosterPath(imagePath);
            holder.resultItemBinding.setResult(result);
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

        private ResultItemBinding resultItemBinding;
        public ResultViewHolder(@NonNull ResultItemBinding resultItemBinding) {
            super(resultItemBinding.getRoot());
            this.resultItemBinding = resultItemBinding;
            resultItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
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
