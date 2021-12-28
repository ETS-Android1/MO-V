package com.example.mo_v;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mo_v.models.MovieModel;
import com.example.mo_v.request.Servicey;
import com.example.mo_v.response.MovieSearchResponse;
import com.example.mo_v.utils.Credentials;
import com.example.mo_v.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button=findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponse();
            }
        });

    }

    private void GetRetrofitResponse() {

        MovieApi movieApi=Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall=movieApi.searchmovie(
                Credentials.API_Key
                ,"Action"
                ,1
               );

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code()==200){
                    Log.v("Tag","The Response"+response.body().toString());
                    List<MovieModel>movies=new ArrayList<>(response.body().getMovies());
                    for(MovieModel movie:movies){

                        Log.v("Tag","the title is : ++"+movie.getTitle());

                    }

                }
                else{
                    try{
                         Log.v("Tag", "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });

    }
}