package com.example.mo_v.niewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mo_v.Repository.MovieRepository;
import com.example.mo_v.models.MovieModel;

import java.util.List;

public class MoviewListViewMOdel extends ViewModel {

     public MovieRepository movieRepository;


    public MoviewListViewMOdel() {
        movieRepository=MovieRepository.getInstance();

    }

    public MutableLiveData<List<MovieModel>> getmMovies() {
        return movieRepository.getmMovies();
    }

    public void searchMovieApi(String query, int pageNumber){

        movieRepository.searchMovieApi(query,pageNumber);
    }
    public void searchNextPage(){

        movieRepository.searchNextMovieApi();
    }

}
