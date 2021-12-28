package com.example.mo_v.utils;

import com.example.mo_v.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    //for search for movie

    @GET("/3/search/movie") //mohema gedannnnnnnn
    Call<MovieSearchResponse>searchmovie(
            @Query("api_key") String Key,
            @Query("query") String Query,
            @Query("page") int page
    );
}



