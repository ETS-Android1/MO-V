package com.example.mo_v.response;


//this class for multiple movie Popular results

import com.example.mo_v.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("total_results")
    @Expose()

    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<MovieModel> movies;

    public int getTotal_count() {
        return total_count;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}
