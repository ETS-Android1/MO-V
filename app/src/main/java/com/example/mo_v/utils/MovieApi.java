package com.example.mo_v.utils;

import com.example.mo_v.models.MovieModel;
import com.example.mo_v.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
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


    //search by ID
    //https://api.themoviedb.org/3/movie/550?api_key=16189ebd9e3212ad7e4a873d57713406
    //3lamet el query is = "?"  "Note"
    //3lamet el Path is = "/"  "Note"

    @GET("3/movie/{movie_id}?")
    Call<MovieModel>getmovie(
            @Path("movie_id") int movie_id
            ,@Query("api_key") String Key
    );

}



