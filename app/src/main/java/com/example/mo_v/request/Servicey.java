package com.example.mo_v.request;

import com.example.mo_v.utils.Credentials;
import com.example.mo_v.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {

    private static Retrofit.Builder retrofitbuilder=
            new Retrofit.Builder()
            .baseUrl(Credentials.Base_url)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit=retrofitbuilder.build();

    private static MovieApi movieApi=retrofit.create(MovieApi.class) ;

    public static   MovieApi getMovieApi(){
        return movieApi;
    }

}
