package com.example.mo_v.response;

//this class for single movie request

import com.example.mo_v.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("results")
    @Expose

    private MovieModel movie;

    private MovieModel getMovie(){
        return movie;
    }


    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
