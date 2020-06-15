package com.example.mvvmretrofitdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.mvvmretrofitdemo.model.MovieDataSource;
import com.example.mvvmretrofitdemo.model.MovieDataSourceFactory;
import com.example.mvvmretrofitdemo.model.MovieRepository;
import com.example.mvvmretrofitdemo.model.Result;
import com.example.mvvmretrofitdemo.service.MovieApiService;
import com.example.mvvmretrofitdemo.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor; //нужен для автоматического управления потоками
    private LiveData<PagedList<Result>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        MovieApiService movieApiService = RetrofitInstance.getService();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(movieApiService,application);
        movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder() // config for page list adapter
                .setEnablePlaceholders(true) //если хотим иметь метса для изображений
                .setInitialLoadSizeHint(10) //изначальное количество элемиентов для загрузки
                .setPageSize(20) //количество, загружаемое в page list
                .setPrefetchDistance(3) //кол-во страниц, загружаемое изначально
                .build();

        executor = Executors.newCachedThreadPool();

        pagedListLiveData = new LivePagedListBuilder<Long, Result>(movieDataSourceFactory,config).setFetchExecutor(executor).build();

    }

    public LiveData<List<Result>> getAllMovieData() {
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
