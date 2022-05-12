package com.example.mo_v;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mo_v.adapter.MovieRecyclerView;
import com.example.mo_v.adapter.OmMovieListener;
import com.example.mo_v.models.MovieModel;
import com.example.mo_v.niewmodels.MoviewListViewMOdel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements OmMovieListener {
    MoviewListViewMOdel moviewListViewMOdel;
    RecyclerView recyclerView;
    RecyclerView recyclerView_search;
    MovieRecyclerView movieRecyclerViewAdapter;
    Button button;

    boolean isPopular= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Toolbar toolbar=findViewById(R.id.tool_bar);
         setSupportActionBar(toolbar);

         SetUpASearchView();

        moviewListViewMOdel=new ViewModelProvider(this).get(MoviewListViewMOdel.class);

        recyclerView=findViewById(R.id.recyclerview);



        ConfigureRecyclerView();
        ObserAnyChange();
        ObservePopulaMovies();

        moviewListViewMOdel.searchMovieApipop(1);



    }

    private void ObservePopulaMovies() {
        moviewListViewMOdel.getmMoviesPop().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels!=null){
                    for(MovieModel movieModel:movieModels){
                        Log.v("Tag","On Change  " +movieModel.getTitle());
                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }


    //observing Any data Changing


    private void ObserAnyChange(){
        moviewListViewMOdel.getmMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels!=null){
                    for(MovieModel movieModel:movieModels){
                        Log.v("Tag","On Change  " +movieModel.getTitle());
                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }






    private void ConfigureRecyclerView(){
        movieRecyclerViewAdapter=new MovieRecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(movieRecyclerViewAdapter);



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    moviewListViewMOdel.searchNextPage();
                }
            }
        });






    }

    @Override
    public void onMovieClick(int position) {
        Intent intent=new Intent(this,DetailsActivity.class);
        intent.putExtra("movie",movieRecyclerViewAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }


    private void SetUpASearchView() {
       final SearchView searchView=findViewById(R.id.search_view);
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               moviewListViewMOdel.searchMovieApi(query,1);

               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               return false;
           }
       });

       searchView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               isPopular=false;
           }
       });


    }






/*
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

    }*/
}