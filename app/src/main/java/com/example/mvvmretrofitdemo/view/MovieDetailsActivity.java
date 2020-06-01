package com.example.mvvmretrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvvmretrofitdemo.R;
import com.example.mvvmretrofitdemo.model.Result;

public class MovieDetailsActivity extends AppCompatActivity {

    private Result result;
    private ImageView imageViewPosterResult;
    private TextView textViewTitleDetail,textViewDescrDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        imageViewPosterResult = findViewById(R.id.imageViewPosterResult);
        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        textViewDescrDetail = findViewById(R.id.textViewDescrDetail);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("movieData")) {
            result = intent.getParcelableExtra("movieData");
            Toast.makeText(this, result.getTitle(), Toast.LENGTH_SHORT).show();
            String imagePath = getString(R.string.poster_base_path) + result.getPosterPath();
            Glide.with(this).load(imagePath).placeholder(R.drawable.progress_circle).into(imageViewPosterResult);
            textViewDescrDetail.setText(result.getOverview());
            textViewTitleDetail.setText(result.getOriginalTitle());
        };
    }
}
