package com.example.mvvmretrofitdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmretrofitdemo.model.MovieRepositiry;
import com.example.mvvmretrofitdemo.model.Result;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepositiry movieRepositiry;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepositiry = new MovieRepositiry(application);
    }

    public LiveData<List<Result>> getAllMovieData() {
        return movieRepositiry.getMutableLiveData();
    }
}
