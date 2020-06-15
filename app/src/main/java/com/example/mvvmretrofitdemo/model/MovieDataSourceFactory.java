package com.example.mvvmretrofitdemo.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.mvvmretrofitdemo.service.MovieApiService;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private MovieApiService movieApiService;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(MovieApiService movieApiService, Application application) {
        this.movieApiService = movieApiService;
        this.application = application;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        movieDataSource = new MovieDataSource(movieApiService,application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
