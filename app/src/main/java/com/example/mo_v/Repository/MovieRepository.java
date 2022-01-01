package com.example.mo_v.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mo_v.models.MovieModel;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private MovieApiClient movieApiClient;

    public static  MovieRepository getInstance()  {
        if(instance==null){
            instance=new MovieRepository();
        }
        return instance;

    }

    public MovieRepository() {
        movieApiClient=MovieApiClient.getInstance();
    }

    public MutableLiveData<List<MovieModel>> getmMovies() {
        return movieApiClient.getmMovies();
    }
    public void searchMovieApi(String query,int pagenumber){
        movieApiClient.searchMovieApi(query,pagenumber);
    }
}
