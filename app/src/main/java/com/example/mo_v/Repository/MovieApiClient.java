package com.example.mo_v.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mo_v.models.MovieModel;

import java.util.List;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    public static MovieApiClient getInstance(){
        if(instance==null){
            instance=new MovieApiClient();

        }
        return instance;
    }

    public MovieApiClient() {
        mMovies=new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getmMovies() {
        return mMovies;
    }
}
