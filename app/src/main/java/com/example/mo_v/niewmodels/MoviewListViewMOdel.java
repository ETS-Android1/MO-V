package com.example.mo_v.niewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mo_v.models.MovieModel;

import java.util.List;

public class MoviewListViewMOdel extends ViewModel {

    private MutableLiveData<List<MovieModel>>mMovies=new MutableLiveData<>();


    public MoviewListViewMOdel() {
    }

    public LiveData<List<MovieModel>> getmMovies() {
        return mMovies;
    }
}
