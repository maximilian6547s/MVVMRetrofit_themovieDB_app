package com.example.mvvmretrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmretrofitdemo.R;
import com.example.mvvmretrofitdemo.adapter.ResultAdapter;
import com.example.mvvmretrofitdemo.databinding.ActivityMainBinding;
import com.example.mvvmretrofitdemo.model.MovieApiResponse;
import com.example.mvvmretrofitdemo.model.Result;
import com.example.mvvmretrofitdemo.service.MovieApiService;
import com.example.mvvmretrofitdemo.service.RetrofitInstance;
import com.example.mvvmretrofitdemo.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        getPopularMovies();
        swipeRefresh = activityMainBinding.swipeRefresh;
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    public void getPopularMovies() {
        mainActivityViewModel.getAllMovieData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> resultsList) {
                results = (ArrayList<Result>) resultsList;
                fillRecyclerView();
            }
        });
    }

    private void fillRecyclerView() {
        recyclerView = activityMainBinding.recyclerView;
        adapter = new ResultAdapter(this, results);
        int spanCount = 0;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 2;
        } else {
            spanCount = 4;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }
}
