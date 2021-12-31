package com.example.mo_v;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mo_v.models.MovieModel;
import com.example.mo_v.niewmodels.MoviewListViewMOdel;
import com.example.mo_v.request.Servicey;
import com.example.mo_v.response.MovieSearchResponse;
import com.example.mo_v.utils.Credentials;
import com.example.mo_v.utils.MovieApi;

import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {
    Button button;
    MoviewListViewMOdel moviewListViewMOdel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviewListViewMOdel=new ViewModelProvider(this).get(MoviewListViewMOdel.class);


        button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    //observing Any data Changing

    private void ObserAnyChange(){
        moviewListViewMOdel.getmMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

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

    private void GEtRetroFitAccordingToID(){
        MovieApi movieApi=Servicey.getMovieApi();
        Call<MovieModel> responseCall =movieApi.getmovie(550,Credentials.API_Key);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code()==200){
                    MovieModel movieModel=response.body();
                    Log.v("Tag","MOvieTilte  "+movieModel.getTitle());
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });

    }
}